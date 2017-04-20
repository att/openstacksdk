/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.util.List;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GatewayInfo implements Serializable {

	@JsonProperty("network_id")
	private String networkId;

	public String getNetworkId() {
		return networkId;
	}

	public void setNetworkId(String id) {
		this.networkId = id;
	}

	@Override public String toString() {
		return "[networkId=" + networkId + "]";
	}
}
