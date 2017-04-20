/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.api;

import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.keystone.v3.model.User;
import com.woorea.openstack.keystone.v3.model.Users;


public class GroupUsersResource extends GenericResource<User, Users> {

	public GroupUsersResource(OpenStackClient client, String path) {
		super(client, path, User.class, Users.class);
	}

}
