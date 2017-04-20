/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.api.extensions;


import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.nova.model.Network;
import com.woorea.openstack.nova.model.Networks;

public class NetworksExtension {
	
	private final OpenStackClient CLIENT;
	
	public NetworksExtension(OpenStackClient client) {
		CLIENT = client;
	}
	
	public List list() {
		return new List();
	}

    public ListExtended listExtended() {
        return new ListExtended();
    }

	public Show show(String id) {
		return new Show(id);
	}

    public ShowExtended showExtended(String id) {
        return new ShowExtended(id);
    }

	public Delete delete(String id) {
		return new Delete(id);
	}

	public Disassociate disassociate(String id) {
		return new Disassociate(id);
	}
	
    public Create create(Network net) { 
        return new Create(net); 
    }

	public CreateExtended createExtended(Network net) { 
	    return new CreateExtended(net); 
	}

	public class List extends OpenStackRequest<Networks> {
		public List() {
			super(CLIENT, HttpMethod.GET, "/os-networks", null, Networks.class);
		}
	}

	/**
	 * This request class is used to list networks with extended attributes
	 *
	 * @author Dewayne Hafenstein
	 * @since Sep 14, 2015
	 * @version $Id$
	 */
    public class ListExtended extends OpenStackRequest<Networks> {
        public ListExtended() {
            super(CLIENT, HttpMethod.GET, "/os-extended-networks", null, Networks.class);
        }
    }

	public class Create extends OpenStackRequest<Network> {
		private Network network;

		public Create(Network network) {
			super(CLIENT, HttpMethod.POST, "/os-networks", Entity.json(network), Network.class);
			this.network = network;
		}
	}

	/**
	 * This class is a request to create a network with extended attributes 
	 *
	 * @author Dewayne Hafenstein
	 * @since Sep 14, 2015
	 * @version $Id$
	 */
    public class CreateExtended extends OpenStackRequest<Network> {
        private Network network;

        public CreateExtended(Network network) {
            super(CLIENT, HttpMethod.POST, "/os-extended-networks", Entity.json(network), Network.class);
            this.network = network;
        }
    }

	public class Show extends OpenStackRequest<Network> {

		public Show(String id) {
			super(CLIENT, HttpMethod.GET, new StringBuilder("/os-networks/").append(id).toString(), null, Network.class);
		}
	}

	/**
	 * This class is used to show the detail information of a specified netork including extended attributes 
	 *
	 * @author Dewayne Hafenstein
	 * @since Sep 14, 2015
	 * @version $Id$
	 */
    public class ShowExtended extends OpenStackRequest<Network> {

        public ShowExtended(String id) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/os-extended-networks/").append(id).toString(), null, Network.class);
        }
    }

	public class Disassociate extends OpenStackRequest<Void> {

		public Disassociate(String id) {
			super(CLIENT, HttpMethod.POST, new StringBuilder("/os-networks/").append(id).toString(), Entity.json("{\"action\":\"disassociate\"}"), Void.class);
			;
		}

	}

	public class Delete extends OpenStackRequest<Void> {

		public Delete(String id) {
			super(CLIENT, HttpMethod.DELETE, new StringBuilder("/os-networks/").append(id).toString(), null, Void.class);
		}

	}

	

}
