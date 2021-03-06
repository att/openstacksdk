/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Routers implements Iterable<Router>, Serializable{
	
	@JsonProperty("routers")
	private List<Router> list;

	/**
	 * @return the list
	 */
	public List<Router> getList() {
		return list;
	}


	/**
	 * @param list the list to set
	 */
	public void setList(List<Router> list) {
		this.list = list;
	}


	public String toString() {
		return "Routers [list=" + list + "]";
	}


	public Iterator<Router> iterator() {
		return list.iterator();
	}
	
}
