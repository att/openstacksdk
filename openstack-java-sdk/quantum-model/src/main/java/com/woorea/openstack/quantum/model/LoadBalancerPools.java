/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoadBalancerPools implements Serializable, Iterable<LoadBalancerPool> {

    @JsonProperty("pools")
    private List<LoadBalancerPool> list;

    /**
     * @return the list
     */
    public List<LoadBalancerPool> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<LoadBalancerPool> list) {
        this.list = list;
    }

    public String toString() {
        return "Pools [list=" + list + "]";
    }

    public Iterator<LoadBalancerPool> iterator() {
        return list.iterator();
    }

}
