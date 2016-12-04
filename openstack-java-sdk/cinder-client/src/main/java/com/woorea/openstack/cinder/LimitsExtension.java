/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.cinder;

import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.cinder.model.Limits;

public class LimitsExtension {

    private final OpenStackClient CLIENT;

    public LimitsExtension(OpenStackClient client) {
        CLIENT = client;
    }

    public List list() {
        return new List();
    }

    public class List extends OpenStackRequest<Limits> {

        public List() {
            super(CLIENT, HttpMethod.GET, "/limits", null, Limits.class);
        }

    }

}
