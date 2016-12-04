/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

/**
 * This interface defines the common behaviors of all token providers. These are classes used by the connector to obtain
 * and manage the security token used to authenticate the user.
 */
public interface OpenStackTokenProvider {

    /**
     * @return The token that is used to authenticate the users access to the API
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection. @see
     *             com.woorea.openstack
     *             .base.client.OpenStackClientConnector#request(com.woorea.openstack.base.client.OpenStackRequest)
     */
    String getToken() throws OpenStackConnectException, OpenStackResponseException;

    /**
     * Forces the token to be expired, and therefore not usable any longer.
     */
    void expireToken();

}
