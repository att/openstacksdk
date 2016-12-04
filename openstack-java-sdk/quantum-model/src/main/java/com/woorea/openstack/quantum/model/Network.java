/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Added network extension support for provider and multi-provider network segments</dd>
 * </dl>
 * Network Extension supporting both Provider networks and Multi-Provider networks. The attributes for both of these
 * network extensions are included. It is the responsibility of users to populate only one or the other. It is also
 * critical that the Mapper is set for serialization inclusion.NON_NULL, or both would be sent to Openstack which would
 * cause an error.
 * 
 */
@SuppressWarnings("serial")
@JsonRootName("network")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Network implements Serializable {

    /**
     * Function to detect and return the network type
     */
    public enum NetworkType {
        BASIC, MULTI_PROVIDER, PROVIDER
    }

    @JsonProperty("admin_state_up")
    private Boolean adminStateUp;

    private String id;

    private Integer mtu;

    private String name;

    @JsonProperty("port_security_enabled")
    private Boolean portSecurityEnabled;

    @JsonProperty("provider:network_type")
    private String providerNetworkType;

    @JsonProperty("provider:physical_network")
    private String providerPhysicalNetwork;

    @JsonProperty("provider:segmentation_id")
    private Integer providerSegmentationId;
    
    @JsonProperty("router:external")
    private String routerExternal;

    private List<Segment> segments;

    private String shared;

    private String status;

    private List<String> subnets;

    @JsonProperty("tenant_id")
    private String tenantId;

    @JsonProperty("vlan_transparent")
    private Boolean vlanTransparent;

    public Boolean getAdminStateUp() {
        return adminStateUp;
    }

    /**
     * @return the id
     */
    @JsonIgnore
    public String getId() {
        return id;
    }

    /**
     * @return the value of mtu
     */
    public Integer getMtu() {
        return mtu;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the netType
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public String getNetType() {
        return getProviderNetworkType();
    }

    @JsonIgnore
    public NetworkType getNetworkType() {
        if (segments != null)
            return NetworkType.MULTI_PROVIDER;
        else if (providerNetworkType != null)
            return NetworkType.PROVIDER;
        else
            return NetworkType.BASIC;
    }

    /**
     * @return the value of portSecurityEnabled
     */
    public Boolean getPortSecurityEnabled() {
        return portSecurityEnabled;
    }

    public String getProviderNetworkType() {
        return providerNetworkType;
    }

    /**
     * @return the providerPhyNet
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public String getProviderPhyNet() {
        return getProviderPhysicalNetwork();
    }

    public String getProviderPhysicalNetwork() {
        return providerPhysicalNetwork;
    }

    /**
     * @return the providerSegID
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public String getProviderSegID() {
        return getProviderSegmentationId() == null ? null : Integer.toString(getProviderSegmentationId());
    }

    public Integer getProviderSegmentationId() {
        return providerSegmentationId;
    }

    /**
     * @return the routerExternal
     */
    public String getRouterExternal() {
        return routerExternal;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    /**
     * @return the shared
     */
    public String getShared() {
        return shared;
    }

    /**
     * @return the status
     */
    @JsonIgnore
    public String getStatus() {
        return status;
    }

    /**
     * @return the subnets
     */
    @JsonIgnore
    public List<String> getSubnets() {
        return subnets;
    }

    /**
     * @return the tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @return the value of vlanTransparent
     */
    public Boolean getVlanTransparent() {
        return vlanTransparent;
    }

    /**
     * @return the adminStateUp
     */
    @JsonIgnore
    public boolean isAdminStateUp() {
        return adminStateUp;
    }

    /**
     * @param adminStateUp
     *            the adminStateUp to set
     */
    public void setAdminStateUp(Boolean adminStateUp) {
        this.adminStateUp = adminStateUp;
    }

    /**
     * @param id
     *            the id to set
     */
    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param mtu the value for mtu
     */
    public void setMtu(Integer mtu) {
        this.mtu = mtu;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param netType
     *            the netType to set
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public void setNetType(String netType) {
        setProviderNetworkType(netType);
    }

    /**
     * @param portSecurityEnabled the value for portSecurityEnabled
     */
    public void setPortSecurityEnabled(Boolean portSecurityEnabled) {
        this.portSecurityEnabled = portSecurityEnabled;
    }

    public void setProviderNetworkType(String providerNetworkType) {
        this.providerNetworkType = providerNetworkType;
    }

    /**
     * @param providerPhyNet
     *            the providerPhyNet to set
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public void setProviderPhyNet(String providerPhyNet) {
        setProviderPhysicalNetwork(providerPhyNet);
    }

    public void setProviderPhysicalNetwork(String providerPhysicalNetwork) {
        this.providerPhysicalNetwork = providerPhysicalNetwork;
    }

    /**
     * @param providerSegID
     *            the providerSegID to set
     * @deprecated
     */
    @Deprecated
    @JsonIgnore
    public void setProviderSegID(String providerSegID) {
        setProviderSegmentationId(providerSegID == null ? null : Integer.parseInt(providerSegID));
    }

    public void setProviderSegmentationId(Integer providerSegmentationId) {
        this.providerSegmentationId = providerSegmentationId;
    }

    /**
     * @param routerExternal
     *            the routerExternal to set
     */
    public void setRouterExternal(String routerExternal) {
        this.routerExternal = routerExternal;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    /**
     * @param shared
     *            the shared to set
     */
    public void setShared(String shared) {
        this.shared = shared;
    }

    /**
     * @param status
     *            the status to set
     */
    @JsonProperty
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param subnets
     *            the subnets to set
     */
    @JsonProperty
    public void setSubnets(List<String> subnets) {
        this.subnets = subnets;
    }

    /**
     * @param tenantId
     *            the tenantId to set
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    };

    /**
     * @param vlanTransparent
     *            the value for vlanTransparent
     */
    public void setVlanTransparent(Boolean vlanTransparent) {
        this.vlanTransparent = vlanTransparent;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuffer buf =
            new StringBuffer("Network [id=" + id + ", name=" + name + ", subnets=" + subnets + ", status=" + status
                + ", admin_state_up=" + adminStateUp + ", tenant_id=" + tenantId + ", shared=" + shared
                + ", router:external=" + routerExternal);
        if (getNetworkType() == NetworkType.PROVIDER)
            buf.append(", provider:physical_network=" + providerPhysicalNetwork + ", provider:network_type="
                + providerNetworkType + ", provider:segmentation_id=" + providerSegmentationId);
        if (getNetworkType() == NetworkType.MULTI_PROVIDER) {
            buf.append(", segments: ");
            for (Segment s : segments)
                buf.append(s.toString()).append(" ");
        }
        buf.append("]");
        return buf.toString();
    }
}
