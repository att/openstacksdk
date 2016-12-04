/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.cinder.model;

import java.io.Serializable;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("volume_type")
public class VolumeTypeForCreate implements Serializable {

    private String name;

    @JsonProperty("extra_specs")
    private Map<String, String> extraSpecs;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the extraSpecs
     */
    public Map<String, String> getExtraSpecs() {
        return extraSpecs;
    }

    /**
     * @param extraSpecs
     *            the extra_specs to set
     */
    public void setExtraSpecs(Map<String, String> extraSpecs) {
        this.extraSpecs = extraSpecs;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "VolumeForCreate [name=" + name + ", metadata=" + extraSpecs + "]";
    }

}
