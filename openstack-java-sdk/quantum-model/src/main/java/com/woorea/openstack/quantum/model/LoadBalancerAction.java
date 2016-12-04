/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.model;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonRootName;

public interface LoadBalancerAction extends Serializable {

    @JsonRootName("health_monitor")
    public static final class AssociateHealthMonitor implements LoadBalancerAction {

        private String id;

        public AssociateHealthMonitor() {
            super();
            // TODO Auto-generated constructor stub
        }

        public AssociateHealthMonitor(String id) {
            super();
            this.id = id;
        }

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id
         *            the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

    }
}
