/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.keystone.model.Service;
import com.woorea.openstack.keystone.model.Services;

public class ServicesResource extends GenericResource<Service, Services> {

	public ServicesResource(OpenStackClient client) {
		super(client, "/services", Service.class, Services.class);
	}

}
