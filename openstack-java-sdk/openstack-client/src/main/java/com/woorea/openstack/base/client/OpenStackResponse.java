/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

import java.io.InputStream;
import java.util.Map;

/**
 * A general interface that defines the common operations that can be performed on all responses
 */
public interface OpenStackResponse {

    /**
     * This method maps the entity returned in the response to an instance of the class indicated.
     * 
     * @param returnType
     *            The type of object to be returned.
     * @return The entity mapped to an instance of the indicated class.
     * @throws OpenStackResponseException
     *             If the entity cannot be mapped because the entity was not found, or there was some form of server
     *             internal error and no entity exists.
     */
    public <T> T getEntity(Class<T> returnType) throws OpenStackResponseException;

    public <T> T getErrorEntity(Class<T> returnType);

    public InputStream getInputStream();

    public String header(String name);

    public Map<String, String> headers();

    public int getStatus();
}
