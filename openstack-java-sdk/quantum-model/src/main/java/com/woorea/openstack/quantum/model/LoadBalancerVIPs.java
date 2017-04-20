/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadBalancerVIPs implements Serializable, Iterable<LoadBalancerVIP> {

    @JsonProperty("vips")
    private List<LoadBalancerVIP> list;

    /**
     * @return the list
     */
    public List<LoadBalancerVIP> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<LoadBalancerVIP> list) {
        this.list = list;
    }

    public String toString() {
        return "VIPs [list=" + list + "]";
    }

    public Iterator<LoadBalancerVIP> iterator() {
        return list.iterator();
    }

}
