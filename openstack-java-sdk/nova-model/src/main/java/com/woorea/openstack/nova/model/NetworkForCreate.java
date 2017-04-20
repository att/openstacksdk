/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * This class is used as a model of a network to be created.
 *
 * @since May 25, 2016
 * @version $Id$
 */
public class NetworkForCreate {

	@JsonProperty("uuid")
	private String id;
	@JsonProperty("fixed_ip")
	private String fixedIp;

    /**
     * An optional ID of an existing port to specify connection to the network and association to a server
     */
    private String port;

	public String getId() {
		return id;
	}

	public String getFixedIp() {
		return fixedIp;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFixedIp(String fixedIp) {
		this.fixedIp = fixedIp;
	}

    /**
     * @return the value of port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port
     *            the value for port
     */
    public void setPort(String port) {
        this.port = port;
    }

}
