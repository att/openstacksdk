/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.api;


import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.nova.model.Extensions;

public class ExtensionsResource {
	
	private final OpenStackClient CLIENT;
	
	public ExtensionsResource(OpenStackClient client) {
		CLIENT = client;
	}
	
	public List list(boolean detail) {
		return new List(detail);
	}
	
	public class List extends OpenStackRequest<Extensions> {

	    public List(boolean detail) {
	    	super(CLIENT, HttpMethod.GET, detail ? "extensions/detail" : "extensions", null, Extensions.class);
	    }
	    
	 }
	
}

