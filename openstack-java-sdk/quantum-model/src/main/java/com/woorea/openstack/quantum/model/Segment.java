/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Segment implements Serializable {

    @JsonProperty("provider:physical_network")
    private String providerPhysicalNetwork;

    @JsonProperty("provider:network_type")
    private String providerNetworkType;

    @JsonProperty("provider:segmentation_id")
    private Integer providerSegmentationId;

    public String getProviderNetworkType() {
        return providerNetworkType;
    }

    public void setProviderNetworkType(String providerNetworkType) {
        this.providerNetworkType = providerNetworkType;
    }

    public String getProviderPhysicalNetwork() {
        return providerPhysicalNetwork;
    }

    public void setProviderPhysicalNetwork(String providerPhysicalNetwork) {
        this.providerPhysicalNetwork = providerPhysicalNetwork;
    }

    public Integer getProviderSegmentationId() {
        return providerSegmentationId;
    }

    public void setProviderSegmentationId(Integer providerSegmentationId) {
        this.providerSegmentationId = providerSegmentationId;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Segment [ provider:physical_network=" + providerPhysicalNetwork +
                ", provider:network_type=" + providerNetworkType +
                ", provider:segmentation_id=" + providerSegmentationId + "]";
    }
}
