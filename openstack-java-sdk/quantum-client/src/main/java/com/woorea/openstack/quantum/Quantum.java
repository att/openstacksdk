/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum;

import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackClientConnector;
import com.woorea.openstack.quantum.api.LoadBalancerResource;
import com.woorea.openstack.quantum.api.NetworksResource;
import com.woorea.openstack.quantum.api.PortsResource;
import com.woorea.openstack.quantum.api.RoutersResource;
import com.woorea.openstack.quantum.api.SubnetsResource;
import com.woorea.openstack.quantum.api.VLANResource;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8-4-2015 </dt>
 * <dd>Added support for load balancers.</dd>
 * </dl>
 */
public class Quantum extends OpenStackClient {

    private final NetworksResource NETWORKS;

    private final PortsResource PORTS;

    private final SubnetsResource SUBNETS;

    private final RoutersResource ROUTERS;

    private final LoadBalancerResource LOAD_BALANCERS;

    private final VLANResource VLANS;

    public Quantum(String endpoint, OpenStackClientConnector connector) {
        super(endpoint, connector);
        NETWORKS = new NetworksResource(this);
        PORTS = new PortsResource(this);
        SUBNETS = new SubnetsResource(this);
        ROUTERS = new RoutersResource(this);
        LOAD_BALANCERS = new LoadBalancerResource(this);
        VLANS = new VLANResource(this);
    }

    public Quantum(String endpoint) {
        this(endpoint, null);
    }

    public NetworksResource networks() {
        return NETWORKS;
    }

    public PortsResource ports() {
        return PORTS;
    }

    public SubnetsResource subnets() {
        return SUBNETS;
    }

    public RoutersResource routers() {
        return ROUTERS;
    }

    public LoadBalancerResource lbaas() {
        return LOAD_BALANCERS;
    }

    public VLANResource vlans() {
        return VLANS;
    }

}
