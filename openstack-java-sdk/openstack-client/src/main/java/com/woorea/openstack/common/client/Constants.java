/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

/**
 * 
 */
package com.woorea.openstack.common.client;

/**
 * 
 * <h2>Revisions</h2>
 * <dl>
 * <dd>Created constants to document configuration properties that can be used
 * to adjust the behavior of the library.</dd>
 * </dl>
 * 
 *
 */
public final class Constants {

	/**
	 * Sets the number of times that the authentication is retried, defaults to
	 * DEFAULT_AUTHENTICATION_RETRIES.
	 */
	public static final String AUTHENTICATION_RETRIES = "com.woorea.openstack.auth.retries";

	/**
	 * The property that specifies if a proxy host is used
	 */
	public static final String PROXY_HOST = "http.proxyHost";

	/**
	 * The property that specifies the port of a proxy host if used. If omitted,
	 * the default is selected by the protocol.
	 */
	public static final String PROXY_PORT = "http.proxyPort";

	/**
	 * Specifies a comma-delimited list of host names or addresses that are
	 * trusted hosts. If an expired or invalid certificate is received from one
	 * of these hosts, it is accepted. If a cert is received from a a host not
	 * in this list, the connection attempt is failed. If not specified, the
	 * default is to not trust any invalid or expired certificates. If set to
	 * "*", then all hosts are accepted.
	 */
	public static final String TRUST_HOST_LIST = "com.woorea.openstack.trust.host.list";

	/**
	 * The default number of retries for authentication attempts
	 */
	public static final String DEFAULT_AUTHENTICATION_RETRIES = "1";
}
