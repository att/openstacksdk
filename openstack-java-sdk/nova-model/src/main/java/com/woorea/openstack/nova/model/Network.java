/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("network")
public class Network implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * If set, reserves all IP addresses after the allowed_end value.
     */
    @JsonProperty("allowed_end")
    private String allowedEnd;

    /**
     * If set, reserves all IP addresses before the allowed_start value.
     */
    @JsonProperty("allowed_start")
    private String allowedStart;

    /**
     * VIFs on this network are connected to this bridge.
     */
    private String bridge;

    /**
     * The bridge is connected to this interface.
     */
    @JsonProperty("bridge_interface")
    private String bridgeInterface;

    /**
     * The broadcast address.
     */
    private String broadcast;

    /**
     * IPv4 subnet.
     */
    private String cidr;

    /**
     * IPv6 subnet.
     */
    @JsonProperty("cidr_v6")
    private String cidrV6;

    /**
     * Created-at timestamp in ISO 8601 format.
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * Deleted flag.
     */
    private Boolean deleted;

    /**
     * Deleted-at timestamp in ISO 8601 format.
     */
    @JsonProperty("deleted_at")
    private String deletedAt;

    /**
     * If you set this parameter to a value other than your default gateway IP address, Compute assumes that your
     * default gateway IP address is external. Default is the default gateway IP address.
     */
    @JsonProperty("dhcp_server")
    private String dhcpServer;

    /**
     * DHCP starting address.
     */
    @JsonProperty("dhcp_start")
    private String dhcpStart;

    /**
     * First DNS.
     */
    private String dns1;

    /**
     * Second DNS.
     */
    private String dns2;

    /**
     * Enables or disables DHCP on the network. Default is true, which enables DHCP. Set to false to disable DHCP.
     */
    @JsonProperty("enable_dhcp")
    private Boolean enableDhcp;

    /**
     * IPv4 gateway.
     */
    private String gateway;

    /**
     * IPv6 gateway.
     */
    @JsonProperty("gateway_v6")
    private String gatewayV6;

    /**
     * Network host.
     */
    private String host;

    /**
     * The network ID.
     */
    private String id;

    /**
     * Injected flag.
     */
    private Boolean injected;

    /**
     * Network label.
     */
    private String label;

    /**
     * Enables you to set the network_device_mtu attribute on each network. This attribute sets the maximum transmission
     * unit (MTU) on a network. Default is enabled.
     */
    private Integer mtu;

    /**
     * Multi host.
     */
    @JsonProperty("multi_host")
    private Boolean multiHost;

    /**
     * The net mask.
     */
    private String netmask;

    /**
     * The IPv6 net mask.
     */
    @JsonProperty("netmask_v6")
    private String netmaskV6;

    /**
     * Network priority.
     */
    private String priority;

    /**
     * Project ID.
     */
    @JsonProperty("project_id")
    private String projectId;

    /**
     * RXTX base factor value for the network.
     */
    @JsonProperty("rxtx_base")
    private String rxtxBase;

    /**
     * If set to true, network has the same DHCP IP address on every host, which enables you to set the
     * share_dhcp_address flag on each network. Default is true.
     */
    @JsonProperty("share_address")
    private Boolean shareAddress;

    /**
     * Updated-at timestamp in ISO 8601 format.
     */
    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * VLAN ID.
     */
    private String vlan;

    /**
     * VPN private address.
     */
    @JsonProperty("vpn_private_address")
    private String vpnPrivateAddress;

    /**
     * VPN public address.
     */
    @JsonProperty("vpn_public_address")
    private String vpnPublicAddress;

    /**
     * VPN public port.
     */
    @JsonProperty("vpn_public_port")
    private String vpnPublicPort;

    /**
     * @return the value of allowedEnd
     */
    public String getAllowedEnd() {
        return allowedEnd;
    }

    /**
     * @return the value of allowedStart
     */
    public String getAllowedStart() {
        return allowedStart;
    }

    /**
     * @return the bridge
     */
    public String getBridge() {
        return bridge;
    }

    /**
     * @return the bridgeInterface
     */
    public String getBridgeInterface() {
        return bridgeInterface;
    }

    /**
     * @return the broadcast
     */
    public String getBroadcast() {
        return broadcast;
    }

    /**
     * @return the cidr
     */
    public String getCidr() {
        return cidr;
    }

    /**
     * @return the cidrV6
     */
    public String getCidrV6() {
        return cidrV6;
    }

    /**
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the deleted
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * @return the deletedAt
     */
    public String getDeletedAt() {
        return deletedAt;
    }

    /**
     * @return the value of dhcpServer
     */
    public String getDhcpServer() {
        return dhcpServer;
    }

    /**
     * @return the dhcpStart
     */
    public String getDhcpStart() {
        return dhcpStart;
    }

    /**
     * @return the dns1
     */
    public String getDns1() {
        return dns1;
    }

    /**
     * @return the dns2
     */
    public String getDns2() {
        return dns2;
    }

    /**
     * @return the value of enableDhcp
     */
    public Boolean getEnableDhcp() {
        return enableDhcp;
    }

    /**
     * @return the gateway
     */
    public String getGateway() {
        return gateway;
    }

    /**
     * @return the gatewayV6
     */
    public String getGatewayV6() {
        return gatewayV6;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the injected
     */
    public Boolean getInjected() {
        return injected;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the value of mtu
     */
    public Integer getMtu() {
        return mtu;
    }

    /**
     * @return the multiHost
     */
    public Boolean getMultiHost() {
        return multiHost;
    }

    /**
     * @return the netmask
     */
    public String getNetmask() {
        return netmask;
    }

    /**
     * @return the netmaskV6
     */
    public String getNetmaskV6() {
        return netmaskV6;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @return the projectId
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @return the rxtxBase
     */
    public String getRxtxBase() {
        return rxtxBase;
    }

    /**
     * @return the value of shareAddress
     */
    public Boolean getShareAddress() {
        return shareAddress;
    }

    /**
     * @return the updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return the vlan
     */
    public String getVlan() {
        return vlan;
    }

    /**
     * @return the value of vpnPrivateAddress
     */
    public String getVpnPrivateAddress() {
        return vpnPrivateAddress;
    }

    /**
     * @return the vpnPublicAddress
     */
    public String getVpnPublicAddress() {
        return vpnPublicAddress;
    }

    /**
     * @return the vpnPublicPort
     */
    public String getVpnPublicPort() {
        return vpnPublicPort;
    }

    /**
     * @param allowedEnd the value for allowedEnd
     */
    public void setAllowedEnd(String allowedEnd) {
        this.allowedEnd = allowedEnd;
    }

    /**
     * @param allowedStart the value for allowedStart
     */
    public void setAllowedStart(String allowedStart) {
        this.allowedStart = allowedStart;
    }

    /**
     * @param bridge the value for bridge
     */
    public void setBridge(String bridge) {
        this.bridge = bridge;
    }

    /**
     * @param bridgeInterface the value for bridgeInterface
     */
    public void setBridgeInterface(String bridgeInterface) {
        this.bridgeInterface = bridgeInterface;
    }

    /**
     * @param broadcast the value for broadcast
     */
    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    /**
     * @param cidr the value for cidr
     */
    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    /**
     * @param cidrV6 the value for cidrV6
     */
    public void setCidrV6(String cidrV6) {
        this.cidrV6 = cidrV6;
    }

    /**
     * @param createdAt the value for createdAt
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @param deleted the value for deleted
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @param deletedAt the value for deletedAt
     */
    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * @param dhcpServer the value for dhcpServer
     */
    public void setDhcpServer(String dhcpServer) {
        this.dhcpServer = dhcpServer;
    }

    /**
     * @param dhcpStart the value for dhcpStart
     */
    public void setDhcpStart(String dhcpStart) {
        this.dhcpStart = dhcpStart;
    }

    /**
     * @param dns1 the value for dns1
     */
    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }

    /**
     * @param dns2 the value for dns2
     */
    public void setDns2(String dns2) {
        this.dns2 = dns2;
    }

    /**
     * @param enableDhcp the value for enableDhcp
     */
    public void setEnableDhcp(Boolean enableDhcp) {
        this.enableDhcp = enableDhcp;
    }

    /**
     * @param gateway the value for gateway
     */
    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    /**
     * @param gatewayV6 the value for gatewayV6
     */
    public void setGatewayV6(String gatewayV6) {
        this.gatewayV6 = gatewayV6;
    }

    /**
     * @param host the value for host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @param id the value for id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param injected the value for injected
     */
    public void setInjected(Boolean injected) {
        this.injected = injected;
    }

    /**
     * @param label the value for label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @param mtu the value for mtu
     */
    public void setMtu(Integer mtu) {
        this.mtu = mtu;
    }

    /**
     * @param multiHost the value for multiHost
     */
    public void setMultiHost(Boolean multiHost) {
        this.multiHost = multiHost;
    }

    /**
     * @param netmask the value for netmask
     */
    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    /**
     * @param netmaskV6 the value for netmaskV6
     */
    public void setNetmaskV6(String netmaskV6) {
        this.netmaskV6 = netmaskV6;
    }

    /**
     * @param priority the value for priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @param projectId the value for projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @param rxtxBase the value for rxtxBase
     */
    public void setRxtxBase(String rxtxBase) {
        this.rxtxBase = rxtxBase;
    }

    /**
     * @param shareAddress the value for shareAddress
     */
    public void setShareAddress(Boolean shareAddress) {
        this.shareAddress = shareAddress;
    }

    /**
     * @param updatedAt the value for updatedAt
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @param vlan the value for vlan
     */
    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    /**
     * @param vpnPrivateAddress the value for vpnPrivateAddress
     */
    public void setVpnPrivateAddress(String vpnPrivateAddress) {
        this.vpnPrivateAddress = vpnPrivateAddress;
    }

    /**
     * @param vpnPublicAddress the value for vpnPublicAddress
     */
    public void setVpnPublicAddress(String vpnPublicAddress) {
        this.vpnPublicAddress = vpnPublicAddress;
    }

    /**
     * @param vpnPublicPort the value for vpnPublicPort
     */
    public void setVpnPublicPort(String vpnPublicPort) {
        this.vpnPublicPort = vpnPublicPort;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Network [id=" + id + ", VLAN=" + vlan + ", vpnPublicAddress=" + vpnPublicAddress + ", vpnPublicPort="
            + vpnPublicPort + ", dhcpStart=" + dhcpStart + ", bridge=" + bridge + ", bridgeInterface="
            + bridgeInterface + ", updatedAt=" + updatedAt + ", deleted=" + deleted + ", vlan=" + vlan + ", broadcast="
            + broadcast + ", netmask=" + netmask + ", injected=" + injected + ", host=" + host + ", multiHost="
            + multiHost + ", createdAt=" + createdAt + ", deletedAt=" + deletedAt + ", cidr=" + cidr + ", cidrV6="
            + cidrV6 + ", gateway=" + gateway + ", gatewayV6=" + gatewayV6 + ", netmaskV6=" + netmaskV6
            + ", projectId=" + projectId + ", rxtxBase=" + rxtxBase + ", dns1=" + dns1 + ", dns2=" + dns2 + ", label="
            + label + ", priority=" + priority + "]";
    }

}
