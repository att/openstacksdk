/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

import java.util.Properties;
import java.util.ServiceLoader;
// import java.util.logging.Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woorea.openstack.common.client.Constants;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dd>Added ability to specify the logger external to the client; added constant definition for properties used in the
 * library to configure its operation; obtained properties to configure behavior</dd>
 * <dd>Change from JUL logging to slf4j for better integration with client applications.</dd>
 * </dl>
 */
public class OpenStackClient {

    protected String endpoint;

    protected OpenStackTokenProvider tokenProvider;

    protected OpenStackClientConnector connector;

    protected Properties properties = new Properties();

    protected Logger logger = LoggerFactory.getLogger(getClass().getName());

    protected OpenStackClientConnector DEFAULT_CONNECTOR;

    // static {
    // ServiceLoader<OpenStackClientConnector> connectorLoader;
    // connectorLoader = ServiceLoader.load(OpenStackClientConnector.class);
    //
    // for (OpenStackClientConnector clientConnector : connectorLoader) {
    // DEFAULT_CONNECTOR = clientConnector;
    // break;
    // }
    // }

    private OpenStackClient() {
        loadDefaultConnector();
    }

    public OpenStackClient(String endpoint) {
        loadDefaultConnector();
        this.endpoint = endpoint;
        this.connector = DEFAULT_CONNECTOR;
    }

    /**
     * Constructs the client for the specified endpoint and using the provided connector
     * 
     * @param endpoint
     *            The endpoint to connect to
     * @param connector
     *            The connector to use. If null, the default connector is determined and loaded.
     */
    public OpenStackClient(String endpoint, OpenStackClientConnector connector) {
        this.endpoint = endpoint;
        if (connector == null) {
            loadDefaultConnector();
            this.connector = DEFAULT_CONNECTOR;
        } else {
            this.connector = connector;
        }
    }

    /**
     * This method replaces the static initializer and is used to load the connector dynamically.
     * <p>
     * Under OSGi containers, the ServiceLoader does not work correctly. In those cases, a constructor has been added to
     * allow the client to specify the connector to be used, and to bypass the use of the service loader.
     * </p>
     */
    private void loadDefaultConnector() {
        ServiceLoader<OpenStackClientConnector> connectorLoader;
        connectorLoader = ServiceLoader.load(OpenStackClientConnector.class);

        for (OpenStackClientConnector clientConnector : connectorLoader) {
            DEFAULT_CONNECTOR = clientConnector;
            break;
        }
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * @return The logger to be used by all connectors on this client
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * @param request
     *            The request to be processed
     * @return The entity returned by the request, if any
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection. @see
     *             com.woorea.openstack
     *             .base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     */
    public <T> OpenStackResponse request(OpenStackRequest<T> request) throws OpenStackConnectException,
        OpenStackResponseException {
        OpenStackResponseException authException = null;
        int retries = Integer.valueOf(Constants.DEFAULT_AUTHENTICATION_RETRIES);
        try {
            retries =
                Integer.valueOf(properties.getProperty(Constants.AUTHENTICATION_RETRIES,
                    Constants.DEFAULT_AUTHENTICATION_RETRIES));
        } catch (NumberFormatException e) {
            // nop
        }

        for (int i = 0; i <= retries; i++) {
            request.endpoint(endpoint);

            if (tokenProvider != null) {
                request.header("X-Auth-Token", tokenProvider.getToken());
            }

            try {
                return connector.request(request);
            } catch (OpenStackResponseException e) {
                if (e.getStatus() != OpenStackResponseStatus.NOT_AUTHORIZED || tokenProvider == null) {
                    throw e;
                }
                authException = e;
                tokenProvider.expireToken();
            }
        }

        throw authException;
    }

    /**
     * @param request
     *            The request to be processed
     * @return The entity returned from the request
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection. @see
     *             com.woorea.openstack
     *             .base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     */
    public <T> T execute(OpenStackRequest<T> request) throws OpenStackConnectException, OpenStackResponseException {
        OpenStackResponse response = request(request);
        return (request.returnType() != null && request.returnType() != Void.class) ? response.getEntity(request
            .returnType()) : null;
    }

    public void property(String property, String value) {
        properties.put(property, value);
    }

    /**
     * @return The properties that govern the operation of this client
     */
    public Properties getProperties() {
        return properties;
    }

    public void setTokenProvider(OpenStackTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public void token(String token) {
        setTokenProvider(new OpenStackSimpleTokenProvider(token));
    }

    public <R> OpenStackRequest<R> get(String path, Class<R> returnType) {
        return new OpenStackRequest<>(this, HttpMethod.GET, path, null, returnType);
    }

}
