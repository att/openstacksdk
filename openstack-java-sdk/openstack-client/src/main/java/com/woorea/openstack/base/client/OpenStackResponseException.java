/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.base.client;

public class OpenStackResponseException extends OpenStackBaseException {

	private static final long serialVersionUID = 7294957362769575271L;

	protected String message;

	protected int status;
	
	// Make the response available for exception handling (includes body)
	protected OpenStackResponse response;

	public OpenStackResponseException(String message, int status) {
		this.message = message;
		this.status = status;
		this.response = null;
	}

	public OpenStackResponseException(String message, int status, OpenStackResponse response) {
		this.message = message;
		this.status = status;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public OpenStackResponse getResponse() {
		return response;
	}

}
