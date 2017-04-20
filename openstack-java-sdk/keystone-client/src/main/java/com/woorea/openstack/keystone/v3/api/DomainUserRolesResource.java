/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.keystone.model.Role;
import com.woorea.openstack.keystone.model.Roles;

public class DomainUserRolesResource extends GenericResource<Role, Roles> {

	public DomainUserRolesResource(OpenStackClient client, String path) {
		super(client, path, Role.class, Roles.class);
	}

	public OpenStackRequest<Void> add(String roleId) {
		return new OpenStackRequest<Void>(CLIENT, HttpMethod.PUT, new StringBuilder(path).append("/").append(roleId).toString(), Entity.json(""), Void.class);
	}
	
	public OpenStackRequest<Void> remove(String roleId) {
		return new OpenStackRequest<Void>(CLIENT, HttpMethod.DELETE, new StringBuilder(path).append("/").append(roleId).toString(), null, Void.class);
	}

}
