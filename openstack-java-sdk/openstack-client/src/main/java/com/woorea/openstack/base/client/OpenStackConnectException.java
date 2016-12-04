/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

/**
 * Custom RuntimeException to report connection errors to Openstack endpoints.
 * Must be a RuntimeException to conform with OpenstackClient interface, which
 * does not declare specific Exceptions.
 */
public class OpenStackConnectException extends OpenStackBaseException {

	private static final long serialVersionUID = 7294957362769575271L;

	public OpenStackConnectException(String message) {
		super(message);
	}

	public OpenStackConnectException(String message, Throwable cause) {
		super(message, cause);
	}
}
