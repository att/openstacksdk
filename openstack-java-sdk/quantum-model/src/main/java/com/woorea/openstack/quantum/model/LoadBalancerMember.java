/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * This represents a Member of a pool for a load balancer.
 * 
 * @since Apr 28, 2015
 * @version $Id$
 */
@SuppressWarnings("serial")
@JsonRootName("member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadBalancerMember implements Serializable {

    /**
     * The id of the member
     */
    private String id;

    /**
     * The id of the pool to create the member in
     */
    @JsonProperty("pool_id")
    private String poolId;

    /**
     * The id of the tenant that owns this member
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * The id of the subnet on which this member exists
     */
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * The IP address of the member
     */
    private String address;

    /**
     * The port on which to listen to client traffic that is associated with the member. A valid value is from 0 to
     * 65535.
     */
    @JsonProperty("protocol_port")
    private Integer port;

    /**
     * The weight of this member inside the pool
     */
    private Integer weight;

    /**
     * The administrative state of the member. A valid value is true (UP) or false (DOWN).
     */
    @JsonProperty("admin_state_up")
    private Boolean state;

    /**
     * @return the value of the tenantId property
     */
    public String getPoolId() {
        return poolId;
    }

    /**
     * @param tenantId
     *            The new value of the tenantId property
     */
    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    /**
     * @return the value of the tenantId property
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId
     *            The new value of the tenantId property
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * @return the value of the subnetId property
     */
    public String getSubnetId() {
        return subnetId;
    }

    /**
     * @param subnetId
     *            The new value of the subnetId property
     */
    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    /**
     * @return the value of the address property
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            The new value of the address property
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the value of the port property
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param weight
     *            The new value of the weight property
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * @return the value of the weight property
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * @param port
     *            The new value of the port property
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the value of the state property
     */
    public Boolean getState() {
        return state;
    }

    /**
     * @param state
     *            The new value of the state property
     */
    public void setState(Boolean state) {
        this.state = state;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("LoadBalancerMember [id=%s, address=%s, port=%s, tenantId=%s, weight=%s]", id, address,
            port, tenantId, weight);
    }

}
