/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.utils;

import java.util.concurrent.ConcurrentHashMap;

import com.woorea.openstack.base.client.OpenStackConnectException;
import com.woorea.openstack.base.client.OpenStackResponseException;
import com.woorea.openstack.base.client.OpenStackTokenProvider;
import com.woorea.openstack.keystone.Keystone;
import com.woorea.openstack.keystone.model.Access;
import com.woorea.openstack.keystone.model.authentication.UsernamePassword;

public class KeystoneTokenProvider {

    protected Keystone keystone;

    protected String username;

    protected String password;

    ConcurrentHashMap<String, Access> hashTenantAccess;

    public KeystoneTokenProvider(String endpoint, String username, String password) {
        this.keystone = new Keystone(endpoint);
        this.username = username;
        this.password = password;
        this.hashTenantAccess = new ConcurrentHashMap<String, Access>();
    }

    /**
     * Returns the access object used to authenticate the user and to authorize access to resources owned by this
     * tenant.
     * 
     * @param tenantName
     *            The name or id of the tenant
     * @return The object to represent the access to resources of this tenant
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection. @see
     *             com.woorea.openstack
     *             .base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     */
    public Access getAccessByTenant(String tenantName) throws OpenStackConnectException, OpenStackResponseException {
        Access access = hashTenantAccess.get(tenantName);
        if (access == null) {
            access =
                keystone.tokens().authenticate(new UsernamePassword(username, password)).withTenantName(tenantName)
                    .execute();
            hashTenantAccess.put(tenantName, access);
        }
        return access;
    }

    public void expireAccessByTenant(String tenantName) {
        hashTenantAccess.remove(tenantName);
    }

    public OpenStackTokenProvider getProviderByTenant(final String tenantName) {
        final KeystoneTokenProvider keystoneTokenProvider = this;
        return new OpenStackTokenProvider() {

            /**
             * @see com.woorea.openstack.base.client.OpenStackTokenProvider#getToken()
             */
            @Override
            public String getToken() throws OpenStackConnectException, OpenStackResponseException {
                return keystoneTokenProvider.getAccessByTenant(tenantName).getToken().getId();
            }

            /**
             * @see com.woorea.openstack.base.client.OpenStackTokenProvider#expireToken()
             */
            @Override
            public void expireToken() {
                keystoneTokenProvider.expireAccessByTenant(tenantName);
            }
        };
    }
}
