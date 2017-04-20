/*******************************************************************************
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.connector;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackClientConnector;
import com.woorea.openstack.base.client.OpenStackConnectException;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.base.client.OpenStackResponse;
import com.woorea.openstack.base.client.OpenStackResponseException;
import com.woorea.openstack.common.client.Constants;
import com.woorea.openstack.common.client.JULWrapper;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Added accessor to obtain reference to OpenStack Client object to obtain configuration properties and logger to
 * use.</dd>
 * <dt>8/4/2015</dt>
 * <dd>Changed logic to defer creation of client until first request so that configuration of request (provided by
 * client) can be passed to the connector. This allows for properties used to configure the operation of the connector
 * to be set on the client object and accessed when the connector is first used. Of course, changing these properties on
 * the client afterward will have no effect.</dd>
 * </dl>
 */
public class JaxRs20Connector implements OpenStackClientConnector {

    private static final int DEFAULT_PROXY_PORT = 8080;
    private Client client = null;
    private ObjectMapper DEFAULT_MAPPER;
    private Logger logger = null;
    private LoggingFilter loggingFilter = null;
    private boolean proxyConnection = false;
    private String proxyHost;
    private int proxyPort;
    private List<Pattern> trustedHostPatterns = new ArrayList<Pattern>();
    private ObjectMapper WRAPPED_MAPPER;

    /**
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

        WebTarget target = client.target(request.endpoint()).path(request.path());

        for (Map.Entry<String, List<Object>> entry : request.queryParams().entrySet()) {
            for (Object o : entry.getValue()) {
                target = target.queryParam(entry.getKey(), o);
            }
        }
        target.register(loggingFilter);
        Invocation.Builder invocation = target.request();

        for (Map.Entry<String, List<Object>> h : request.headers().entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (Object v : h.getValue()) {
                sb.append(String.valueOf(v));
            }
            invocation.header(h.getKey(), sb);
        }

        Entity<?> entity =
            (request.entity() == null) ? null : Entity.entity(request.entity().getEntity(), request.entity()
                .getContentType());

        try {
            if (entity != null) {
                return new JaxRs20Response(invocation.method(request.method().name(), entity));
            }

            if (HttpMethod.PUT == request.method()) {
                return new JaxRs20Response(invocation.method(request.method().name(),
                    Entity.entity("", MediaType.APPLICATION_JSON)));
            }

            return new JaxRs20Response(invocation.method(request.method().name()));
        } catch (ClientErrorException e) {
            throw new OpenStackResponseException(e.getResponse().getStatusInfo().toString(), e.getResponse()
                .getStatus());
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
    private synchronized <T> void connect(OpenStackRequest<T> request) throws NoSuchAlgorithmException,
        KeyManagementException {
        /*
         * If we've already set up the client, return it. Otherwise, set it up and cache the client for subsequent use.
         */
        if (client != null) {
            return;
        }

        OpenStackClient osClient = request.getOpenStackClient();
        Properties properties = osClient.getProperties();
        logger = osClient.getLogger();
        loggingFilter = new LoggingFilter(new JULWrapper(logger), true);

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

        ClientConfig config = new ClientConfig();
        ClientBuilder builder = ClientBuilder.newBuilder();
        builder.withConfig(config);

        /*
         * Process the proxy if defined.
         */
        proxyHost = properties.getProperty(Constants.PROXY_HOST);
        if (proxyHost != null) {
            proxyHost = proxyHost.trim();
        }

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

            if (proxyConnection) {
                config.property(ClientProperties.PROXY_URI, String.format("%s:%d", proxyHost, proxyPort));
            }
        }

        /*
         * If this is an HTTPS connection, and a trusted hosts list has been provided, then we also need to set up the
         * host name verifier
         */
        if (request.endpoint().startsWith("https") && !trustedHostPatterns.isEmpty()) {
            builder.hostnameVerifier(getHostnameVerifier());
        }

        SSLContext context = null;
        context = SSLContext.getInstance("SSL");
        context.init(null, null, null);

        SslConfigurator sslConfig = SslConfigurator.newInstance();
        client = builder.sslContext(sslConfig.createSSLContext()).build();

        DEFAULT_MAPPER = new ObjectMapper();

        DEFAULT_MAPPER.setSerializationInclusion(Include.NON_NULL);
        DEFAULT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        DEFAULT_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        DEFAULT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        DEFAULT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        WRAPPED_MAPPER = new ObjectMapper();

        WRAPPED_MAPPER.setSerializationInclusion(Include.NON_NULL);
        WRAPPED_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        WRAPPED_MAPPER.enable(SerializationFeature.WRAP_ROOT_VALUE);
        WRAPPED_MAPPER.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        WRAPPED_MAPPER.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        WRAPPED_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        WRAPPED_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        client.register(new JacksonFeature()).register(new ContextResolver<ObjectMapper>() {

            @Override
            public ObjectMapper getContext(Class<?> type) {
                return type.getAnnotation(JsonRootName.class) == null ? DEFAULT_MAPPER : WRAPPED_MAPPER;
            }

        });

        client.register(new ClientRequestFilter() {

            @Override
            public void filter(ClientRequestContext requestContext) throws IOException {
                requestContext.getHeaders().remove("Content-Language");
                requestContext.getHeaders().remove("Content-Encoding");
            }
        });

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
}
