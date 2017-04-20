/*******************************************************************************
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.connector;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.woorea.openstack.base.client.OpenStackResponse;
import com.woorea.openstack.base.client.OpenStackResponseException;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Added reference to response object to exception</dd>
 * </dl>
 */
public class JaxRs20Response implements OpenStackResponse {
	
	private Response response;
	
	public JaxRs20Response(Response response) {
		this.response = response;
	}

    /**
     * @see com.woorea.openstack.base.client.OpenStackResponse#getEntity(java.lang.Class)
     */
	@Override
    public <T> T getEntity(Class<T> returnType) throws OpenStackResponseException {
		if(response.getStatus() >= 400) {
			throw new OpenStackResponseException(response.getStatusInfo().getReasonPhrase(),
					response.getStatusInfo().getStatusCode(), this);
		}
		return response.readEntity(returnType);
	}

	@Override
	public <T> T getErrorEntity(Class<T> returnType) {
		if(response.getStatus() >= 400 && response.hasEntity()) {
			return response.readEntity(returnType);
		}
		return null;
	}
	

	@Override
	public InputStream getInputStream() {
		return (InputStream) response.getEntity();
	}

	@Override
	public String header(String name) {
		return response.getHeaderString(name);
	}

	@Override
	public Map<String, String> headers() {
		Map<String, String> headers = new HashMap<String, String>();
		for(String k : response.getHeaders().keySet()) {
			headers.put(k, response.getHeaderString(k));
		}
		return headers;
	}

    @Override
    public int getStatus() {
        return response.getStatus();

    }
}
