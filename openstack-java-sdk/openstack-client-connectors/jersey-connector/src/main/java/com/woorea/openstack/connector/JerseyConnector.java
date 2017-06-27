/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.connector;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
// import java.util.logging.Logger;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.slf4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.impl.ClientRequestImpl;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;
import com.sun.jersey.core.header.OutBoundHeaders;
import com.sun.jersey.core.impl.provider.entity.StringProvider;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackClientConnector;
import com.woorea.openstack.base.client.OpenStackConnectException;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.base.client.OpenStackResponse;
import com.woorea.openstack.base.client.OpenStackResponseException;
import com.woorea.openstack.common.client.Constants;
import com.woorea.openstack.common.client.JULWrapper;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dd>Added accessor to obtain reference to OpenStack Client object to obtain configuration properties and logger to
 * use.</dd>
 * <dd>Changed logic to defer creation of client until first request so that configuration of request (provided by
 * client) can be passed to the connector. This allows for properties used to configure the operation of the connector
 * to be set on the client object and accessed when the connector is first used. Of course, changing these properties on
 * the client afterward will have no effect.</dd>
 * </dl>
 */
public class JerseyConnector implements OpenStackClientConnector {

    private static final int DEFAULT_PROXY_PORT = 8080;
    private Client client = null;
    private LoggingFilter filter = null;
    private Logger logger;
    private boolean proxyConnection = false;
    private String proxyHost;
    private int proxyPort;
    private List<Pattern> trustedHostPatterns = new ArrayList<Pattern>();

    /**
     * Defer construction to first request
     */
    public JerseyConnector() {
        // ClientConfig clientConfig = new DefaultClientConfig();
        // clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        // clientConfig.getClasses().add(OpenStackObjectMapper.class);
        // client = Client.create(clientConfig);
    }

    /**
     * Process a request.
     * <p>
     * If this is the first request processed by this instance, create the web client object and configure it according
     * to the properties set on the OpenStackClient making the request. This allows a user of the OpenStackClient to
     * configure properties to control the use of proxies, trusted hosts, HTTPS processing options, etc
     * </p>
     * 
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection. @see
     *             com.woorea.openstack
     *             .base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     * @see com.woorea.openstack.base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     */
    @Override
    public <T> OpenStackResponse request(OpenStackRequest<T> request) throws OpenStackResponseException,
        OpenStackConnectException {

        try {
            connect(request);
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new SecurityException(e.getMessage(), e);
        }

        WebResource target = client.resource(request.endpoint()).path(request.path());
        for (Map.Entry<String, List<Object>> entry : request.queryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                target = target.queryParam(entry.getKey(), String.valueOf(o));
            }
        }
        target.addFilter(filter);
        MultivaluedMap<String, Object> headers = new OutBoundHeaders();
        for (Map.Entry<String, List<Object>> h : request.headers().entrySet()) {
            for (Object v : h.getValue()) {
                headers.add(h.getKey(), v);
            }
        }
        if (request.entity() != null && request.entity().getContentType() != null) {
            headers.add("Content-Type", request.entity().getContentType());
        } else {
            headers.add("Content-Type", "application/json");
        }
        try {
            ClientResponse response = null;
            if (request.entity() != null && request.entity().getEntity() != null) {
                response =
                    target.getHeadHandler().handle(
                        new ClientRequestImpl(target.getURI(), request.method().name(), request.entity().getEntity(),
                            headers));
            } else {
                response =
                    target.getHeadHandler().handle(
                        new ClientRequestImpl(target.getURI(), request.method().name(), null, headers));
            }
            return new JerseyResponse(response);
        } catch (ClientHandlerException e) {
            throw new OpenStackConnectException(e.getMessage(), e);
        } catch (UniformInterfaceException e) {
            throw new OpenStackResponseException(e.getResponse().getClientResponseStatus().getReasonPhrase(), e
                .getResponse().getStatus());
        }
    }

    /**
     * This method is used to lazy-initialize the connector on the first request.
     * <p>
     * The method uses the request object to obtain a reference to the client making the request. The configuration of
     * the client is then inspected to determine if a proxy is needed and how to handle invalid or expired certificates
     * when the connection is over HTTPS. The logger that was setup for the client is also used to set up the logging
     * filter and to log any messages from the connector. Additional configuration settings could be used in the future.
     * </p>
     * 
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private synchronized <T> void connect(OpenStackRequest<T> request) throws KeyManagementException,
        NoSuchAlgorithmException {
        /*
         * If we've already set up the client, return it. Otherwise, set it up and cache the client for subsequent use.
         */
        if (client != null) {
            return;
        }

        OpenStackClient osClient = request.getOpenStackClient();
        Properties properties = osClient.getProperties();
        logger = osClient.getLogger();
        filter = new LoggingFilter(new JULWrapper(logger));
        ClientConfig clientConfig = new DefaultClientConfig();

        /*
         * Process the trusted hosts list if provided. In this case, we convert each entry in the comma-delimited list
         * to a regular expression pattern and cache the patterns. We will use the patterns in the host name verifier to
         * determine if the host is trusted or not.
         */
        String temp = properties.getProperty(Constants.TRUST_HOST_LIST);
        if (temp != null && temp.length() > 0) {
            String[] tokens = temp.split(",");
            for (String token : tokens) {
                if (token != null && token.length() > 0) {
                    StringBuffer buffer = new StringBuffer(token);
                    for (int index = 0; index < buffer.length(); index++) {
                        switch (buffer.charAt(index)) {
                        // Convert * to .*
                            case '*':
                                buffer.insert(index, ".");
                                index++;
                                break;
                            // Convert + to .+
                            case '+':
                                buffer.insert(index, ".");
                                index++;
                                break;
                            // Convert . to \. (escaped period)
                            case '.':
                                buffer.replace(index, index + 1, "\\.");
                                index++;
                                break;
                        }
                    }

                    trustedHostPatterns.add(Pattern.compile(buffer.toString()));
                }
            }
        }

        /*
         * Process the proxy if defined.
         */
        proxyHost = properties.getProperty(Constants.PROXY_HOST);
        if (proxyHost != null && proxyHost.length() > 0) {
            temp = properties.getProperty(Constants.PROXY_PORT);
            if (temp != null) {
                temp = temp.trim();
            }

            if (temp == null || temp.length() == 0) {
                proxyPort = DEFAULT_PROXY_PORT;
                proxyConnection = true;
            } else {
                try {
                    proxyPort = Integer.valueOf(temp);
                } catch (NumberFormatException e) {
                    logger.error(String.format("Invalid port \"%s\" specified for proxy host \"%s\", a direct "
                        + "connection will be used", temp, proxyHost));
                }

                if (proxyPort > 0 && proxyPort < 65536) {
                    proxyConnection = true;
                } else {
                    logger.error(String.format("Invalid port \"%d\" specified for proxy host \"%s\", a direct "
                        + "connection will be used", proxyPort, proxyHost));
                }
            }
        }

        /*
         * If this is an HTTPS connection, and a trusted hosts list has been provided, then we also need to set up the
         * host name verifier and allow all certificates and just allow or disallow based on trusted hosts
         */
        if (request.endpoint().startsWith("https") && !trustedHostPatterns.isEmpty()) {
            clientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
                new HTTPSProperties(getHostnameVerifier(), getSSLContext()));
        }

        /*
         * Finally, add the object mapper classes
         */
        clientConfig.getClasses().add(JacksonJaxbJsonProvider.class);
        clientConfig.getClasses().add(OpenStackObjectMapper.class);
        clientConfig.getClasses().add(StringProvider.class);

        /*
         * And create the client either with or without proxy
         */
        if (proxyConnection) {
            logger.debug(String.format("Connecting using proxy %s:%d", proxyHost, proxyPort));
            client = new Client(new URLConnectionClientHandler(new HttpURLConnectionFactory() {
                Proxy p = null;

                @Override
                public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
                    if (p == null) {
                        if (proxyHost != null && proxyPort != 0) {
                            p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                        } else {
                            p = Proxy.NO_PROXY;
                        }
                    }
                    return (HttpURLConnection) url.openConnection(p);
                }
            }), clientConfig);

        } else {
            logger.debug(String.format("Direct connection to %s", request.endpoint()));
            proxyHost = null;
            client = Client.create(clientConfig);
        }
    }

    /**
     * This host name verifier is used if the protocol is HTTPS AND a trusted hosts list has been provided. Otherwise,
     * the default behavior is used (requiring valid, unexpired certificates).
     * 
     * @return A host-name verifier that verifies the hosts based on the trusted hosts list. Note, the trusted hosts
     *         list can include wild-card characters * and +.
     */
    private HostnameVerifier getHostnameVerifier() {
        return new HostnameVerifier() {

            @Override
            public boolean verify(String hostName, SSLSession arg1) {
                for (Pattern trustedHostPattern : trustedHostPatterns) {
                    if (trustedHostPattern.matcher(hostName).matches()) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * This SSLContext is used only when the protocol is HTTPS AND a trusted hosts list has been provided. In that case,
     * this SSLContext accepts all certificates, whether they are expired or not, and regardless of the CA that issued
     * them.
     * 
     * @return An SSL context that does not validate certificates
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    // nop
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    // nop
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }
        }, new java.security.SecureRandom());
        return sslContext;
    }

    @Provider
    public static class OpenStackObjectMapper implements ContextResolver<ObjectMapper> {
        static ObjectMapper DEFAULT_MAPPER;
        static ObjectMapper WRAPPED_MAPPER;
        static {
            DEFAULT_MAPPER = new ObjectMapper();
            DEFAULT_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
            DEFAULT_MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            DEFAULT_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            DEFAULT_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            DEFAULT_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

            WRAPPED_MAPPER = new ObjectMapper();
            WRAPPED_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
            WRAPPED_MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            WRAPPED_MAPPER.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            WRAPPED_MAPPER.enable(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
            WRAPPED_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            WRAPPED_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
            WRAPPED_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return type.getAnnotation(JsonRootName.class) == null ? DEFAULT_MAPPER : WRAPPED_MAPPER;
        }
    }
}
