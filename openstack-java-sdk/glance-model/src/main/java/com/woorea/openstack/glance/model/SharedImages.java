/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.glance.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SharedImages implements Iterable<SharedImage>, Serializable {

	@JsonProperty("shared_images")
	private List<SharedImage> list;

	/**
	 * @return the list
	 */
	public List<SharedImage> getList() {
		return list;
	}

	@Override
	public Iterator<SharedImage> iterator() {
		return list.iterator();
	}
	
}