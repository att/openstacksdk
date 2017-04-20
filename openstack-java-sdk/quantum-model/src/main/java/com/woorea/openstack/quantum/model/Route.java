/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The definition of a route on a subnet
 * 
 * @since May 07, 2015
 * @version $Id$
 */

@SuppressWarnings("serial")
@JsonRootName("subnet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route implements Serializable {

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("nexthop")
    private String nexthop;

    /**
     * default constructor
     */
    public Route() {
    }

    /**
     * @param destination
     * @param nextHop
     */
    public Route(String destination, String nextHop) {
        this.destination = destination;
        this.nexthop = nextHop;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        Route other = (Route) obj;
        return destination.equals(other.destination) && nexthop.equals(other.nexthop);
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @retrun nexthop
     */
    public String getNexthop() {
        return nexthop;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * @param destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @param nexthop
     */
    public void setNexthop(String nexthop) {
        this.nexthop = nexthop;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Route [destination=" + destination + ", nexthop=" + nexthop + "]";
    }

}
