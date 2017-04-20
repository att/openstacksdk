/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.glance.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>08-04-2015 </dt>
 * <dd>Added support for paging results of lists of images</dd>
 * </dl>
 */
public class Images implements Iterable<Image>, Serializable {

	@JsonProperty("images")
	private List<Image> list;

    @JsonProperty("next")
    private String next;

    @JsonProperty("first")
    private String first;

    @JsonProperty("schema")
    private String schema;

	/**
	 * @return the list
	 */
	public List<Image> getList() {
		return list;
	}

	@Override
	public Iterator<Image> iterator() {
		return list.iterator();
	}
	
    /**
     * @return the next
     */
    public String getNext() {
        return next;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }
}
