/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.connector;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.jersey.api.client.ClientResponse;
import com.woorea.openstack.base.client.OpenStackResponse;
import com.woorea.openstack.base.client.OpenStackResponseException;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Added reference to response object to exception</dd>
 * </dl>
 */
public class JerseyResponse implements OpenStackResponse {

	private ClientResponse response;

	public JerseyResponse(ClientResponse response) {
		this.response = response;
	}

    /**
     * @see com.woorea.openstack.base.client.OpenStackResponse#getEntity(java.lang.Class)
     */
	@Override
    public <T> T getEntity(Class<T> returnType) throws OpenStackResponseException {
		if(response.getStatus() >= 400) {
			throw new OpenStackResponseException(response.getClientResponseStatus().getReasonPhrase(), 
					response.getStatus(), this);
		}
		if(response.hasEntity() && returnType != null && Void.class != returnType) {
			return response.getEntity(returnType);
		}
        return null;
	}

	@Override
	public <T> T getErrorEntity(Class<T> returnType) {
		if(response.getStatus() >= 400 && response.hasEntity()) {
			return response.getEntity(returnType);
		}
		return null;
	}

	@Override
	public InputStream getInputStream() {
		if(response.hasEntity()) {
			return response.getEntityInputStream();
		}
        return null;
	}

	@Override
	public String header(String name) {
		return response.getHeaders().getFirst(name);
	}

	@Override
	public Map<String, String> headers() {
		Map<String, String> headers = new HashMap<String, String>();
		for(String k : response.getHeaders().keySet()) {
			headers.put(k, response.getHeaders().getFirst(k));
		}
		return headers;
	}

    @Override
    public int getStatus() {
        return response.getStatus();

    }
}
