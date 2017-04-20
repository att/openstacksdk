/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.glance.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageMembers implements Iterable<ImageMember>, Serializable {

	@JsonProperty("members")
	private List<ImageMember> list;

	/**
	 * @return the list
	 */
	public List<ImageMember> getList() {
		return list;
	}

	@Override
	public Iterator<ImageMember> iterator() {
		return list.iterator();
	}
	
}