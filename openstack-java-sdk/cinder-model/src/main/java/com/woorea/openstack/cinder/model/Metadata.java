/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.cinder.model;

import java.util.Map;

public class Metadata {

    private Map<String, String> metadata;

    /**
     * @return the metadata
     */
    public Map<String, String> getMetadata() {
        return metadata;
    }

    /**
     * Set the metadata
     *
     * @param metadata
     */
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Metadata [metadata=" + metadata + "]";
    }

}
