/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoadBalancerMembers implements Serializable, Iterable<LoadBalancerMember> {

    @JsonProperty("members")
    private List<LoadBalancerMember> list;

    /**
     * @return the list
     */
    public List<LoadBalancerMember> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<LoadBalancerMember> list) {
        this.list = list;
    }

    public String toString() {
        return "Members [list=" + list + "]";
    }

    public Iterator<LoadBalancerMember> iterator() {
        return list.iterator();
    }

}
