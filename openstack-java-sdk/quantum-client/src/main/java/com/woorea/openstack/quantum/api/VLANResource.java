/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.api;


import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.quantum.model.Network;
import com.woorea.openstack.quantum.model.Networks;

public class VLANResource {

	private final OpenStackClient CLIENT;

	public VLANResource(OpenStackClient client) {
		CLIENT = client;
	}

	public List list() {
		return new List();
	}

    public Create create(Network net) {
		return new Create(net);
	}

	public Show show(String netId){
		return new Show(netId);
	}

	public class List extends OpenStackRequest<Networks> {

		public List() {
		    super(CLIENT, HttpMethod.GET, "networks", null, Networks.class);
		}
	}

	public class Create extends OpenStackRequest<Network> {

        public Create(Network net) {
		    super(CLIENT, HttpMethod.POST, "networks", Entity.json(net), Network.class);
		}
	}

	public class Show extends OpenStackRequest<Network> {

		public Show(String id) {
		    super(CLIENT, HttpMethod.GET, buildPath("networks/", id), null, Network.class);
		}
	}
}
