/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.v3.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.woorea.openstack.keystone.model.Service;

public class Services implements Iterable<Service>,  Serializable {

	@JsonProperty("services")
	private List<Service> list;

	/**
	 * @return the list
	 */
	public List<Service> getList() {
		return list;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Services [list=" + list + "]";
	}

	@Override
	public Iterator<Service> iterator() {
		return list.iterator();
	}
	
}
