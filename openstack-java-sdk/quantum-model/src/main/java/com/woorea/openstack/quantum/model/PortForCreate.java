/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @deprecated Please use {@link Port} directly.
 */
@SuppressWarnings("serial")
@JsonRootName("port")
@Deprecated
public class PortForCreate extends Port {
    // unused
}
