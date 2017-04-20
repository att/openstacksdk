/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.api.extensions;


import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.nova.model.KeyPair;
import com.woorea.openstack.nova.model.KeyPairs;

public class KeyPairsExtension {
	
	private final OpenStackClient CLIENT;
	
	public KeyPairsExtension(OpenStackClient client) {
		CLIENT = client;
	}
	
	public List list() {
		return new List();
	}

	public Create create(String name, String publicKey) {
		KeyPair keyPairForCreate = new KeyPair(name, publicKey);
		return new Create(keyPairForCreate);
	}

	public Create create(String name) {
		return create(name, null);
	}

	public Delete delete(String name) {
		return new Delete(name);
	}

	public class Create extends OpenStackRequest<KeyPair> {

		private KeyPair keyPairForCreate;

		public Create(KeyPair keyPairForCreate) {
			super(CLIENT, HttpMethod.POST, "/os-keypairs", Entity.json(keyPairForCreate), KeyPair.class);
			this.keyPairForCreate = keyPairForCreate;
		}

	}

	public class Delete extends OpenStackRequest<Void> {

		private String name;

		public Delete(String name) {
			super(CLIENT, HttpMethod.DELETE, new StringBuilder("/os-keypairs/").append(name).toString(), null, Void.class);
		}

	}

	public class List extends OpenStackRequest<KeyPairs> {

		public List() {
			super(CLIENT, HttpMethod.GET, "/os-keypairs", null, KeyPairs.class);
		}

	}
	
}
