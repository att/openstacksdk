/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

/**
 * The connector is a pluggable implementation that can use several different approaches and technologies to connect to
 * and access the OpenStack API. This interface defines the common behavior that all connectors must expose.
 */
public interface OpenStackClientConnector {

    /**
     * This method is used to process the specified request object and to return any response indicated.
     * 
     * @param request
     *            The request object to be processed
     * @return The response from processing the request
     * @throws OpenStackConnectException
     *             If the connection to the provider cannot be established. This exception indicates that the provider
     *             is not reachable, or that some failure occurred attempting to open the connection to the provider.
     * @throws OpenStackResponseException
     *             If the provider responds with some form of error regarding the request, such as unauthorized. This is
     *             an indication that there is a problem with the request itself, not the connection.
     */
    public <T> OpenStackResponse request(OpenStackRequest<T> request) throws OpenStackConnectException,
        OpenStackResponseException;

}
