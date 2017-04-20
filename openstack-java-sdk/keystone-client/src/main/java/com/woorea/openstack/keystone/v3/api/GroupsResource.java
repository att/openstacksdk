/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.keystone.v3.model.Group;
import com.woorea.openstack.keystone.v3.model.Groups;

public class GroupsResource extends GenericResource<Group, Groups> {

	public GroupsResource(OpenStackClient client) {
		super(client, "/groups", Group.class, Groups.class);
	}
	
	public DomainUserRolesResource userRoles(String domainId, String userId) {
		return new DomainUserRolesResource(CLIENT, new StringBuilder(path).append("/").append(domainId).append("/users/").append(userId).append("/roles").toString());
	}

}
