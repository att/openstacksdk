/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.heat.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Stacks implements Iterable<Stack>, Serializable {
    @JsonProperty("stacks")
    private List<Stack> list;

    @Override
    public Iterator<Stack> iterator() {
        return list.iterator();
    }
}
