/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Domains implements Iterable<Domain>,  Serializable {

	@JsonProperty("domains")
	private List<Domain> list;

	/**
	 * @return the list
	 */
	public List<Domain> getList() {
		return list;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Domains [list=" + list + "]";
	}

	@Override
	public Iterator<Domain> iterator() {
		return list.iterator();
	}
	
}
