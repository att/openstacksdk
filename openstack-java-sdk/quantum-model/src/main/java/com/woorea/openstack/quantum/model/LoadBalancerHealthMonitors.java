/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class LoadBalancerHealthMonitors implements Serializable, Iterable<LoadBalancerHealthMonitor> {

    @JsonProperty("health_monitors")
    private List<LoadBalancerHealthMonitor> list;

    /**
     * @return the list
     */
    public List<LoadBalancerHealthMonitor> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<LoadBalancerHealthMonitor> list) {
        this.list = list;
    }

    public String toString() {
        return "Health Monitors [list=" + list + "]";
    }

    public Iterator<LoadBalancerHealthMonitor> iterator() {
        return list.iterator();
    }

}
