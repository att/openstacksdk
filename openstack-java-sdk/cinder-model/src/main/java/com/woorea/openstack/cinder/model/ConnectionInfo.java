/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.cinder.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("connection_info")
public class ConnectionInfo implements Serializable {

    @JsonProperty("driver_volume_type")
    private String driverVolumeType;

    private Map<String, Object> data;

    /**
     * @return the driverVolumeType
     */
    public String getDriverVolumeType() {
        return driverVolumeType;
    }

    /**
     * @param driverVolumeType
     *            the driverVolumeType to set
     */
    public void setDriverVolumeType(String driverVolumeType) {
        this.driverVolumeType = driverVolumeType;
    }

    /**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConnectionInfo [driverVolumeType=" + driverVolumeType + "," +
                " data=" + data + "]";
    }

}
