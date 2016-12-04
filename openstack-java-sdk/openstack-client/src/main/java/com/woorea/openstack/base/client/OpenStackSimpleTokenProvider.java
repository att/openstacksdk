/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

/**
 * 
 *
 */
public class OpenStackSimpleTokenProvider implements OpenStackTokenProvider {

    private String token;

    /**
     * @param token
     */
	public OpenStackSimpleTokenProvider(String token) {
		this.token = token;
	}

    /**
     * @see com.woorea.openstack.base.client.OpenStackTokenProvider#getToken()
     */
	@Override
	public String getToken() {
		return this.token;
	}

    /**
     * @see com.woorea.openstack.base.client.OpenStackTokenProvider#expireToken()
     */
	@Override
	public void expireToken() {
        // nop
	}
}
