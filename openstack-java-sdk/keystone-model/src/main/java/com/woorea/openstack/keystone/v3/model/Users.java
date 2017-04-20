/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.woorea.openstack.keystone.model.User;

public class Users implements Iterable<User>,  Serializable {

	@JsonProperty("users")
	private List<User> list;

	/**
	 * @return the list
	 */
	public List<User> getList() {
		return list;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [list=" + list + "]";
	}

	@Override
	public Iterator<User> iterator() {
		return list.iterator();
	}
	
}
