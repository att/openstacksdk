/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Groups implements Iterable<Group>,  Serializable {

	@JsonProperty("groups")
	private List<Group> list;

	/**
	 * @return the list
	 */
	public List<Group> getList() {
		return list;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Groups [list=" + list + "]";
	}

	@Override
	public Iterator<Group> iterator() {
		return list.iterator();
	}
	
}
