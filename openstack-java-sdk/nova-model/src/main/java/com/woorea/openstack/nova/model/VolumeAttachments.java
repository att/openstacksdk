/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolumeAttachments implements Iterable<VolumeAttachment>, Serializable {

	@JsonProperty("volumeAttachments")
	private List<VolumeAttachment> list;

	/**
	 * @return the list
	 */
	public List<VolumeAttachment> getList() {
		return list;
	}

	@Override
	public Iterator<VolumeAttachment> iterator() {
		return list.iterator();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VolumeAttachments [list=" + list + "]";
	}

}
