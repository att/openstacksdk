/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * This represents a VIP (Virtual IP) for a load balancer. The VIP is the entry point into the load balancer.
 * 
 * @since Apr 28, 2015
 * @version $Id$
 */
@SuppressWarnings("serial")
@JsonRootName("pool")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadBalancerPool implements Serializable {

    /**
     * The id of the Pool
     */
    private String id;

    /**
     * The human readable name of the pool
     */
    private String name;

    /**
     * The id of the tenant that owns this pool
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * The id of the subnet associated with this pool
     */
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * The id of the VIP associated with this pool
     */
    @JsonProperty("vip_id")
    private String vipId;

    /**
     * The protocol of the pool, which is TCP, HTTP, or HTTPS.
     */
    private String protocol;

    /**
     * The load-balancer algorithm, which is round-robin, least-connections, and so on. This value, which must be
     * supported, is dependent on the load-balancer provider. Round-robin must be supported.
     */
    @JsonProperty("lb_method")
    private String method;

    /**
     * The provider
     */
    // TODO - What is a provider
    private String provider;

    /**
     * A list of load balancer member id's that are associated with the pool. These DO NOT correspond to instance ids
     */
    private List<String> members;

    /**
     * A list of health monitor ids that are associated with this pool
     */
    @JsonProperty("health_monitors")
    private List<String> monitors;

    /**
     * The administrative state of the Pool. A valid value is true (UP) or false (DOWN).
     */
    @JsonProperty("admin_state_up")
    private Boolean state;

    /**
     * @return the value of the name property
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The new value of the name property
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the value of the protocol property
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol
     *            The new value of the protocol property
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
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

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<String> monitors) {
        this.monitors = monitors;
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
        return String.format("LoadBalancerPool [id=%s, name=%s, method=%s tenantId=%s, subnetId=%s, vipId=%s]", id,
            name, method, tenantId, subnetId, vipId);
    }

}
