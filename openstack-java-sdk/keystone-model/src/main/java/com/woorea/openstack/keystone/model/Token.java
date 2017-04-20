/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.keystone.model;

import java.util.Calendar;

public final class Token {

	private String id;

	private Calendar issued_at;

	private Calendar expires;

	private Tenant tenant;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the issued_at
	 */
	public Calendar getIssued_at() {
	  return issued_at;
	}

	/**
	 * @return the expires
	 */
	public Calendar getExpires() {
		return expires;
	}

	/**
	 * @return the tenant
	 */
	public Tenant getTenant() {
		return tenant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Token [id=" + id + ", Issued_at=" + issued_at + ", expires=" + expires + ", tenant="
          + tenant + "]";
	}

}