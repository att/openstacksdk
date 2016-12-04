/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

/**
 * A common abstract parent of all Openstack Exception types, allowing calling classes the choice to catch all error
 * exceptions together.
 * <p>
 * This class was defined as an extension of {@link RuntimeException}, which made it unchecked and therefore not
 * explicitly understood at the various points where it might be thrown. This complicated recovery logic, so the class
 * was changed to specialize {@link Exception} instead, and all places where it could be thrown were explicitly
 * identified.
 * </p>
 */
public abstract class OpenStackBaseException extends Exception {
    private static final long serialVersionUID = 1L;

    /*
     * Implement only the basic constructors
     */
    public OpenStackBaseException() {
    }

    public OpenStackBaseException(String message) {
        super(message);
    }

    public OpenStackBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
