/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for List of Services
 *
 */
public class Services implements Iterable<Service> {

    @JsonProperty("services")
    private List<Service> list;

    public List<Service> getList() {
        return list;
    }

    public void setList(List<Service> list) {
        this.list = list;
    }

    @Override
    public Iterator<Service> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return "Services [list=" + list + "]";
    }
}
