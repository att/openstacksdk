/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.keystone.v3.model.Role;
import com.woorea.openstack.keystone.v3.model.Roles;

public class DomainGroupRolesResource extends GenericResource<Role, Roles> {

	public DomainGroupRolesResource(OpenStackClient client, String path) {
		super(client, path, Role.class, Roles.class);
	}

}
