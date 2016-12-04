/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dd>Added accessor to obtain reference to OpenStack Client object to obtain configuration properties and logger to
 * use.</dd>
 * </dl>
 */
public class OpenStackRequest<R> {

    private OpenStackClient client;

    public OpenStackRequest() {

    }

    public OpenStackRequest(OpenStackClient client, HttpMethod method, CharSequence path, Entity<?> entity,
                            Class<R> returnType) {
        this.client = client;
        this.method = method;
        this.path = new StringBuilder(path);
        this.entity = entity;
        this.returnType = returnType;
        header("Accept", "application/json");
    }

    private String endpoint;

    private HttpMethod method;

    private StringBuilder path = new StringBuilder();

    private Map<String, List<Object>> headers = new HashMap<>();

    private Entity<?> entity;

    private Class<R> returnType;

    public OpenStackRequest<R> endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    /**
     * @return The openStackClient object
     */
    public OpenStackClient getOpenStackClient() {
        return client;
    }

    public String endpoint() {
        return endpoint;
    }

    public OpenStackRequest<R> method(HttpMethod method) {
        this.method = method;
        return this;
    }

    public HttpMethod method() {
        return method;
    }

    public OpenStackRequest<R> path(String path) {
        this.path.append(path);
        return this;
    }

    public String path() {
        return path.toString();
    }

    public OpenStackRequest<R> header(String name, Object value) {
        if (value != null) {
            headers.put(name, Arrays.asList(value));
        }
        return this;
    }

    public Map<String, List<Object>> headers() {
        return headers;
    }

    public <T> Entity<T> entity(T entity, String contentType) {
        return new Entity<>(entity, contentType);
    }

    public Entity<?> entity() {
        return entity;
    }

    public <T> Entity<T> json(T entity) {
        return entity(entity, "application/json");
    }

    public void returnType(Class<R> returnType) {
        this.returnType = returnType;
    }

    public Class<R> returnType() {
        return returnType;
    }

    /**
     * Executes the request that has been constructed
     * 
     * @return The response from the request
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection.
     */
    public R execute() throws OpenStackConnectException, OpenStackResponseException {
        return client.execute(this);
    }

    /**
     * @return The response to the request execution
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection. @see
     *             com.woorea.openstack
     *             .base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     */
    public OpenStackResponse request() throws OpenStackConnectException, OpenStackResponseException {
        return client.request(this);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "OpenStackRequest [endpoint=" + endpoint + ", method=" + method + ", path=" + path + ", headers="
            + headers + ", entity=" + entity + ", returnType=" + returnType + "]";
    }

    private Map<String, List<Object>> queryParams = new LinkedHashMap<>();

    public Map<String, List<Object>> queryParams() {
        return queryParams;
    }

    public OpenStackRequest<R> queryParam(String key, Object value) {
        if (queryParams.containsKey(key)) {
            List<Object> values = queryParams.get(key);
            values.add(value);
        } else {
            List<Object> values = new ArrayList<>();
            values.add(value);
            queryParams.put(key, values);
        }

        return this;
    }

    protected static String buildPath(String... elements) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String element : elements) {
            stringBuilder.append(element);
        }

        return stringBuilder.toString();
    }

    /**
     * This method will parse the query string which is a collection of name value pairs and put then in queryParam hash
     * map
     * 
     * @param queryString
     *            The query string to be parsed
     * @return The request object
     */
    public OpenStackRequest<R> queryString(String queryString) {
        if (queryString != null) {
            String[] params = queryString.split("&");
            for (String param : params) {
                String[] s = param.split("=");
                if (s[0] != null && s[1] != null) {
                    queryParam(s[0], s[1]);
                }
            }
        }
        return this;
    }
}
