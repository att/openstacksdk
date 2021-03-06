/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woorea.openstack.quantum.model.Subnet.IpVersion;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dd>Converted route information from a String to a Route.</dd>
 * </dl>
 */
public class SubnetTest {

    private static final String POOL_END = "poolEnd";

    private static final String POOL_START = "poolStart";

    private static final String TENANT_ID = "tenantId";

    private static final String NETWORK_ID = "networkId";

    private static final String NAME = "name";

    private static final String HOST_ROUTE_DESTINATION = "hostDestination";

    private static final String HOST_ROUTE_NEXTHOP = "hostNextHop";

    private static final Route HOST_ROUTE = new Route(HOST_ROUTE_DESTINATION, HOST_ROUTE_NEXTHOP);

    private static final String GATEWAY = "gw";

    private static final boolean ENABLE_DHCP = true;

    private static final String ID = "testId";

    private static final String CIDR = "10.0.0.0/8";

    private static final String DNS_SERVER = "dnsServer";

    private static final IpVersion IP_VERSION = IpVersion.IPV4;

    /**
     * JSON with read only attributes.
     */
    private static final String SUBNET_JSON = "{" + "  \"subnet\" : {" + "    \"id\" : \"" + ID + "\"" + "  }" + "}";

    private ObjectMapper objectMapper;

    private String serializedSubnet;

    @Before
    public void setUp() throws Exception {
        objectMapper = PortTest.initializeObjectMapper();
    }

    @Test
    public void testSerialization() throws Exception {
        Subnet subnet = new Subnet();
        subnet.setId(ID);
        subnet.setCidr(CIDR);
        subnet.setDnsNames(Arrays.asList(DNS_SERVER));
        subnet.setEnableDHCP(ENABLE_DHCP);
        subnet.setIpversion(IP_VERSION);
        subnet.setGw(GATEWAY);
        subnet.setHostRoutes(Arrays.asList(HOST_ROUTE));
        subnet.setName(NAME);
        subnet.setNetworkId(NETWORK_ID);
        subnet.setTenantId(TENANT_ID);

        Pool pool = new Pool();
        pool.setStart(POOL_START);
        pool.setEnd(POOL_END);
        subnet.setList(Arrays.asList(pool));

        serializedSubnet = objectMapper.writeValueAsString(subnet);
        assertThat(serializedSubnet, not(containsString(ID)));
        assertThat(serializedSubnet, containsString(CIDR));
        assertThat(serializedSubnet, containsString(DNS_SERVER));
        assertThat(serializedSubnet, containsString("\"enable_dhcp\" : " + ENABLE_DHCP));
        assertThat(serializedSubnet, containsString(Integer.toString(IP_VERSION.code())));
        assertThat(serializedSubnet, containsString(GATEWAY));
        assertThat(serializedSubnet, containsString(HOST_ROUTE_DESTINATION));
        assertThat(serializedSubnet, containsString(HOST_ROUTE_NEXTHOP));
        assertThat(serializedSubnet, containsString(NAME));
        assertThat(serializedSubnet, containsString(NETWORK_ID));
        assertThat(serializedSubnet, containsString(TENANT_ID));
        assertThat(serializedSubnet, containsString(POOL_START));
        assertThat(serializedSubnet, containsString(POOL_END));
    }

    @Test
    public void testSerializationEmpty() throws Exception {
        Subnet subnet = new Subnet();
        serializedSubnet = objectMapper.writeValueAsString(subnet);

        assertThat(serializedSubnet, containsString("\"subnet\" : { }"));
    }

    @Test
    public void testDeserializationReadOnlyFields() throws Exception {
        Subnet subnet = objectMapper.readValue(SUBNET_JSON, Subnet.class);

        assertThat(subnet.getId(), is(equalTo(ID)));
    }

    @Test
    public void testDeserializationAfterSerialization() throws Exception {
        testSerialization();
        Subnet subnet = objectMapper.readValue(serializedSubnet, Subnet.class);

        assertThat(subnet.getCidr(), is(equalTo(CIDR)));
        assertThat(subnet.getDnsNames(), hasItem(equalTo(DNS_SERVER)));
        assertThat(subnet.isEnableDHCP(), is(equalTo(ENABLE_DHCP)));
        assertThat(subnet.getIpversion(), is(equalTo(IP_VERSION)));
        assertThat(subnet.getGw(), is(equalTo(GATEWAY)));
        assertThat(subnet.getHostRoutes(), hasItem(equalTo(HOST_ROUTE)));
        assertThat(subnet.getName(), is(equalTo(NAME)));
        assertThat(subnet.getNetworkId(), is(equalTo(NETWORK_ID)));
        assertThat(subnet.getTenantId(), is(equalTo(TENANT_ID)));
        assertThat(subnet.getList(), hasItem(new CustomMatcher<Pool>("a Pool object with start " + POOL_START
            + " and end " + POOL_END) {

            @Override
            public boolean matches(Object pool) {
                return pool instanceof Pool && POOL_START.equals(((Pool) pool).getStart())
                    && POOL_END.equals(((Pool) pool).getEnd());
            }
        }));
    }
}
