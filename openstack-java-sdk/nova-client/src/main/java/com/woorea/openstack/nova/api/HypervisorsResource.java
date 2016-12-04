/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.api;

import java.util.HashMap;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.nova.model.Hypervisor;
import com.woorea.openstack.nova.model.Hypervisors;

/**
 * Model for HypervisorsResource
 *
 */

public class HypervisorsResource {

    private final OpenStackClient CLIENT;

    public HypervisorsResource(OpenStackClient client) {
        this.CLIENT = client;
    }

    public List list() {
        return new List();
    }

    public class List extends OpenStackRequest<Hypervisors> {

        public List() {
            super(CLIENT, HttpMethod.GET, "/os-hypervisors", null, Hypervisors.class);
        }
    }

}
