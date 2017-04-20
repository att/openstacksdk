/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.keystone.v3.model.Role;
import com.woorea.openstack.keystone.v3.model.Roles;
import com.woorea.openstack.keystone.v3.model.Users;

public class RolesResource extends GenericResource<Role, Roles> {

	public RolesResource(OpenStackClient client) {
		super(client, "/roles", Role.class, Roles.class);
	}
	
	public OpenStackRequest<Users> users(String domainId, String userId) {
		return CLIENT.get(new StringBuilder(path).append("/").append(domainId).append("/users/").append(userId).append("/roles").toString(), Users.class);
	}

}
