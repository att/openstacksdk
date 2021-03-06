/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouterForAddInterface implements Serializable {

		@JsonProperty("subnet_id")
		String subnetId;
		String routerId;

		public String getSubnetId() {
			return subnetId;
		}

		public void setSubnetId(String subnetId) {
			this.subnetId = subnetId;
		}

		public String getRouterId() {
			return routerId;
		}

		public void setRouterId(String routerId) {
			this.routerId = routerId;
		}
	}
