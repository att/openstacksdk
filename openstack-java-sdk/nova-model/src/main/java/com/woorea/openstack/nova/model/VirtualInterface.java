/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The values that define a virtual interface
 *
 * @since Sep 14, 2015
 * @version $Id$
 */
public class VirtualInterface implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("mac_addr")
    private String macAddress;

    @JsonProperty("net_id")
    private String networkId;

    /**
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the value for id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the value of macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress the value for macAddress
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the value of networkId
     */
    public String getNetworkId() {
        return networkId;
    }

    /**
     * @param networkId the value for networkId
     */
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VirtualInterface [id=" + id + ", macAddress=" + macAddress + ", networkId=" + networkId + "]";
    }

}
