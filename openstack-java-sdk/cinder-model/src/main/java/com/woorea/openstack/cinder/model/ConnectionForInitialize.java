/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.cinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonRootName("os-initialize_connection")
public class ConnectionForInitialize implements Serializable {

    @JsonProperty("connector")
    private Map<String, Object> connector = new HashMap<String, Object>();

    /**
     * @return the connector
     */
    public Map<String, Object> getConnector() {
        return connector;
    }

    /**
     * @param connector
     *            the connector to set
     */
    public void setConnector(Map<String, Object> connector) {
        this.connector = connector;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConnectionForInitialize [connector=" + connector + "]";
    }

}
