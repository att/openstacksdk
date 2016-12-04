/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.utils;

import java.util.List;

import com.woorea.openstack.keystone.model.Access.Service;

public class KeystoneUtils {

	public static String findEndpointURL(List<Service> serviceCatalog, String type, String region, String facing) {
		for(Service service : serviceCatalog) {
			if(type.equals(service.getType())) {
				for(Service.Endpoint endpoint : service.getEndpoints()) {
					if(region == null || region.equals(endpoint.getRegion())) {
						if(endpoint.getPublicURL() != null && facing.equals("public")) {
							return endpoint.getPublicURL();
						} else if(endpoint.getInternalURL() != null && facing.equals("internal")) {
							return endpoint.getInternalURL();
						} else if(endpoint.getAdminURL() != null && facing.equals("admin")) {
							return endpoint.getAdminURL();
						}
					}
				}
			}
		}
		throw new RuntimeException("endpoint url not found");
	}

}
