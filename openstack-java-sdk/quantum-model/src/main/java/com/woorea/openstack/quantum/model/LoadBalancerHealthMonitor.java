/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * This represents a Health Monitor (HM) for a load balancer. The HM is the mechanism that determines if an element in
 * the pool is OK to accept traffic
 * 
 * @since Apr 28, 2015
 * @version $Id$
 */
@SuppressWarnings("serial")
@JsonRootName("health_monitor")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LoadBalancerHealthMonitor implements Serializable {

    @JsonRootName("pool")
    @JsonIgnoreProperties(value = {
        "status", "status_description"
    })
    public static class Pool implements Serializable {

        @JsonProperty("pool_id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    /**
     * The Id of the Health Monitor
     */
    private String id;

    /**
     * The id of the tenant that owns this HM
     */
    @JsonProperty("tenant_id")
    private String tenantId;

    /**
     * The type of probe sent by the load balancer to verify the member state, which is PING, TCP, HTTP, or HTTPS
     */
    private String type;

    /**
     * The time, in seconds, between sending probes to members.
     */
    private Integer delay;

    /**
     * Number of allowed connection failures before changing the status of the member to INACTIVE. A valid value is from
     * 1 to 10.
     */
    @JsonProperty("max_retries")
    private Integer maxRetries;

    /**
     * The maximum number of seconds for a monitor to wait for a connection to be established before it times out. This
     * value must be less than the delay value.
     */
    private Integer timeout;

    /**
     * Expected HTTP codes for a passing HTTP(S) monitor.
     */
    @JsonProperty("expected_codes")
    private String expectedCodes;

    /**
     * The HTTP method that the monitor uses for requests.
     */
    @JsonProperty("http_method")
    private String httpMethod;

    /**
     * The HTTP path of the request sent by the monitor to test the health of a member. Must be a string beginning with
     * a forward slash (/).
     */
    @JsonProperty("url_path")
    private String urlPath;

    /**
     * The administrative state of the VIP. A valid value is true (UP) or false (DOWN).
     */
    @JsonProperty("admin_state_up")
    private Boolean state;

    /**
     * Pools
     */
    private List<Pool> pools;

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
     * @return the value of the type property
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            The new value of the type property
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the value of the delay property
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     * @param delay
     *            The new value of the delay property
     */
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    /**
     * @return the value of the maxRetries property
     */
    public Integer getMaxRetries() {
        return maxRetries;
    }

    /**
     * @param maxRetries
     *            The new value of the maxRetries property
     */
    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    /**
     * @return the value of the timeout property
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @param timeout
     *            The new value of the timeout property
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * @return the value of the expectedCodes property
     */
    public String getExpectedCodes() {
        return expectedCodes;
    }

    /**
     * @param expectedCodes
     *            The new value of the expectedCodes property
     */
    public void setExpectedCodes(String expectedCodes) {
        this.expectedCodes = expectedCodes;
    }

    /**
     * @return the value of the httpMethod property
     */
    public String getHttpMethod() {
        return httpMethod;
    }

    /**
     * @param httpMethod
     *            The new value of the httpMethod property
     */
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    /**
     * @return the value of the urlPath property
     */
    public String getUrlPath() {
        return urlPath;
    }

    /**
     * @param urlPath
     *            The new value of the urlPath property
     */
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
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

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String
            .format(
                "LoadBalancerHealthMonitor [id=%s, tenantId=%s, type=%s, delay=%s, maxRetries=%s, timeout=%s, expectedCodes=%s, httpMethod=%s, urlPath=%s]",
                id, tenantId, type, delay, maxRetries, timeout, expectedCodes, httpMethod, urlPath);
    }

}
