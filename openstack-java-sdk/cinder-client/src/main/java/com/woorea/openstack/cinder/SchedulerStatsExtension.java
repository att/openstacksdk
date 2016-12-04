/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/


package com.woorea.openstack.cinder;

import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.cinder.model.Pools;

/**
 * Cinder Scheduler Stats Management
 */
public class SchedulerStatsExtension {

    private final OpenStackClient CLIENT;

    public SchedulerStatsExtension(OpenStackClient client) {
        this.CLIENT = client;
    }

    public List list(boolean detail) {
        return new List(detail);
    }

    public class List extends OpenStackRequest<Pools> {

        public List(boolean detail) {
            super(CLIENT, HttpMethod.GET, (new StringBuilder("/scheduler-stats/get_pools")).append(detail ? "?detail=True":""), null, Pools.class);
        }
    }

}
