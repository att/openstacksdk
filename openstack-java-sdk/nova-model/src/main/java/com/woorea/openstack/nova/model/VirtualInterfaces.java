/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A list of virtual interfaces for a server
 *
 * @since Sep 14, 2015
 * @version $Id$
 */
public class VirtualInterfaces implements Iterable<VirtualInterface>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("virtual_interfaces")
    private List<VirtualInterface> list;

    /**
     * @return the list
     */
    public List<VirtualInterface> getList() {
        return list;
    }

    @Override
    public Iterator<VirtualInterface> iterator() {
        return list.iterator();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VirtualInterfaces [list=" + list + "]";
    }
}
