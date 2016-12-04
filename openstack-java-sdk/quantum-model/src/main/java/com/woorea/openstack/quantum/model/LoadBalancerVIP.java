/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * This represents a VIP (Virtual IP) for a load balancer. The VIP is the entry point into the load balancer.
 * 
 * @since Apr 28, 2015
 * @version $Id$
 */
@SuppressWarnings("serial")
@JsonRootName("vip")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadBalancerVIP implements Serializable {

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    public class SessionPersistence {
        /**
         * The type of persistence to use. SOURCE_IPP, HTTP_COOKIE, APP_COOKIE
         */
        private String type;

        /**
         * An optional parameter for the APP_COOKIE type
         */
        @JsonProperty("cookie_name")
        private String cookieName = null;

        /*
         * @JsonCreator public SessionPersistence(@JsonProperty("type") String type, @JsonProperty("cookie_name") String
         * cookie_name) { this.type = type; this.cookieName = cookie_name; }
         */

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }
    }

    /**
     * The Id of the VIP
     */
    private String id;

    /**
     * The human readable name of the VIP
     */
    private String name;

    /**
     * The id of the tenant that owns this VIP
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * The id of the subnet on which to allocate the VIP address
     */
    @JsonProperty("subnet_id")
    private String subnetId;

    /**
     * The IP address of the VIP
     */
    private String address;

    /**
     * The protocol of the VIP address. A valid value is TCP, HTTP, or HTTPS.
     */
    private String protocol;

    /**
     * The port on which to listen to client traffic that is associated with the VIP address. A valid value is from 0 to
     * 65535.
     */
    @JsonProperty("protocol_port")
    private Integer port;

    /**
     * The id of the pool with which the VIP is associated
     */
    @JsonProperty("pool_id")
    private String poolId;

    /**
     * The maximum number of connections allowed for the VIP. Default is -1, meaning no limit.
     */
    @JsonProperty("connection_limit")
    private Integer connectionLimit;

    /**
     * The administrative state of the VIP. A valid value is true (UP) or false (DOWN).
     */
    @JsonProperty("admin_state_up")
    private Boolean state;

    @JsonProperty("session_persistence")
    private SessionPersistence sessionPersistance;

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
     * @return the value of the port property
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port
     *            The new value of the port property
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * @return the value of the poolId property
     */
    public String getPoolId() {
        return poolId;
    }

    /**
     * @param poolId
     *            The new value of the poolId property
     */
    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    /**
     * @return the value of the connectionLimit property
     */
    public Integer getConnectionLimit() {
        return connectionLimit;
    }

    /**
     * @param connectionLimit
     *            The new value of the connectionLimit property
     */
    public void setConnectionLimit(Integer connectionLimit) {
        this.connectionLimit = connectionLimit;
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
        return String
            .format(
                "LoadBalancerVIP [id=%s, name=%s, address=%s, port=%s, protocol=%s, tenantId=%s, subnetId=%s, poolId=%s, connectionLimit=%s]",
                id, name, address, port, protocol, tenantId, subnetId, poolId, connectionLimit);
    }

}
