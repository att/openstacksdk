/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.quantum.api;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.quantum.model.LoadBalancerAction.AssociateHealthMonitor;
import com.woorea.openstack.quantum.model.LoadBalancerHealthMonitor;
import com.woorea.openstack.quantum.model.LoadBalancerHealthMonitors;
import com.woorea.openstack.quantum.model.LoadBalancerMember;
import com.woorea.openstack.quantum.model.LoadBalancerMembers;
import com.woorea.openstack.quantum.model.LoadBalancerPool;
import com.woorea.openstack.quantum.model.LoadBalancerPools;
import com.woorea.openstack.quantum.model.LoadBalancerVIP;
import com.woorea.openstack.quantum.model.LoadBalancerVIPs;

public class LoadBalancerResource {

    private final OpenStackClient CLIENT;

    private final HealthMonitor healthMonitor;

    private final Member member;

    private final Pool pool;

    private final VIP vip;

    public LoadBalancerResource(OpenStackClient client) {
        CLIENT = client;
        healthMonitor = new HealthMonitor(client);
        member = new Member(client);
        pool = new Pool(client);
        vip = new VIP(client);
    }

    public HealthMonitor HealthMonitor() {
        return this.healthMonitor;
    }

    public Member Member() {
        return this.member;
    }

    public Pool Pool() {
        return this.pool;
    }

    public VIP VIP() {
        return this.vip;
    }

    public class HealthMonitor {

        private final OpenStackClient CLIENT;

        public HealthMonitor(OpenStackClient client) {
            CLIENT = client;
        }

        public List list() {
            return new List();
        }

        public Create create(LoadBalancerHealthMonitor hm) {
            /*
             * Will fail if not null hm.setId(null); hm.setPools(null);
             */
            return new Create(hm);
        }

        public Update update(LoadBalancerHealthMonitor hm) {
            return new Update(hm);
        }

        public Delete delete(String hmId) {
            return new Delete(hmId);
        }

        public Show show(String hmId) {
            return new Show(hmId);
        }

        public class List extends OpenStackRequest<LoadBalancerHealthMonitors> {

            public List() {
                super(CLIENT, HttpMethod.GET, "lb/health_monitors", null, LoadBalancerHealthMonitors.class);
            }
        }

        public class Query extends OpenStackRequest<LoadBalancerHealthMonitor> {

            public Query(LoadBalancerHealthMonitor hm) {
                // super(subnet);
                // target = target.path("v2.0").path("subnets");
                // target = queryParam(target);
                // return target.request(MediaType.APPLICATION_JSON).get(Subnets.class);
            }
        }

        public class Create extends OpenStackRequest<LoadBalancerHealthMonitor> {

            public Create(LoadBalancerHealthMonitor hm) {
                super(CLIENT, HttpMethod.POST, "lb/health_monitors", Entity.json(hm), LoadBalancerHealthMonitor.class);
            }
        }

        public class Update extends OpenStackRequest<LoadBalancerHealthMonitor> {

            public Update(LoadBalancerHealthMonitor hm) {
                super(CLIENT, HttpMethod.PUT, buildPath("lb/health_monitors/", hm.getId()), Entity.json(hm),
                    LoadBalancerHealthMonitor.class);
            }
        }

        public class Show extends OpenStackRequest<LoadBalancerHealthMonitor> {

            public Show(String id) {
                super(CLIENT, HttpMethod.GET, buildPath("lb/health_monitors/", id), null,
                    LoadBalancerHealthMonitor.class);
            }
        }

        public class Delete extends OpenStackRequest<Void> {

            public Delete(String id) {
                super(CLIENT, HttpMethod.DELETE, buildPath("lb/health_monitors/", id), null, Void.class);
            }
        }
    }

    public class Member {

        private final OpenStackClient CLIENT;

        public Member(OpenStackClient client) {
            CLIENT = client;
        }

        public List list() {
            return new List();
        }

        public Create create(LoadBalancerMember member) {
            return new Create(member);
        }

        public Update update(LoadBalancerMember member) {
            return new Update(member);
        }

        public Delete delete(String memberId) {
            return new Delete(memberId);
        }

        public Show show(String memberId) {
            return new Show(memberId);
        }

        public class List extends OpenStackRequest<LoadBalancerMembers> {

            public List() {
                super(CLIENT, HttpMethod.GET, "lb/members", null, LoadBalancerMembers.class);
            }
        }

        public class Query extends OpenStackRequest<LoadBalancerMember> {

            public Query(LoadBalancerVIP member) {
                // super(subnet);
                // target = target.path("v2.0").path("subnets");
                // target = queryParam(target);
                // return target.request(MediaType.APPLICATION_JSON).get(Subnets.class);
            }
        }

        public class Create extends OpenStackRequest<LoadBalancerMember> {

            public Create(LoadBalancerMember member) {
                super(CLIENT, HttpMethod.POST, "lb/members", Entity.json(member), LoadBalancerMember.class);
            }
        }

        public class Update extends OpenStackRequest<LoadBalancerMember> {

            public Update(LoadBalancerMember member) {
                super(CLIENT, HttpMethod.PUT, buildPath("lb/members/", member.getId()), Entity.json(member),
                    LoadBalancerMember.class);
            }
        }

        public class Show extends OpenStackRequest<LoadBalancerMember> {

            public Show(String id) {
                super(CLIENT, HttpMethod.GET, buildPath("lb/members/", id), null, LoadBalancerMember.class);
            }
        }

        public class Delete extends OpenStackRequest<Void> {

            public Delete(String id) {
                super(CLIENT, HttpMethod.DELETE, buildPath("lb/members/", id), null, Void.class);
            }
        }
    }

    public class Pool {

        private final OpenStackClient CLIENT;

        public Pool(OpenStackClient client) {
            CLIENT = client;
        }

        public List list() {
            return new List();
        }

        public Create create(LoadBalancerPool pool) {
            return new Create(pool);
        }

        public Update update(LoadBalancerPool pool) {
            return new Update(pool);
        }

        public Delete delete(String poolId) {
            return new Delete(poolId);
        }

        public Show show(String poolId) {
            return new Show(poolId);
        }

        public AssociateMonitor associateMonitor(String poolId, String healthMonitorId) {
            AssociateHealthMonitor hm = new AssociateHealthMonitor();
            hm.setId(healthMonitorId);
            return new AssociateMonitor(poolId, hm);
        }

        public DisassociateMonitor disassociateMonitor(String poolId, String healthMonitorId) {
            return new DisassociateMonitor(poolId, healthMonitorId);
        }

        public class List extends OpenStackRequest<LoadBalancerPools> {

            public List() {
                super(CLIENT, HttpMethod.GET, "lb/pools", null, LoadBalancerPools.class);
            }
        }

        public class Query extends OpenStackRequest<LoadBalancerPool> {

            public Query(LoadBalancerPool pool) {
                // super(subnet);
                // target = target.path("v2.0").path("subnets");
                // target = queryParam(target);
                // return target.request(MediaType.APPLICATION_JSON).get(Subnets.class);
            }
        }

        public class Create extends OpenStackRequest<LoadBalancerPool> {

            public Create(LoadBalancerPool pool) {
                super(CLIENT, HttpMethod.POST, "lb/pools", Entity.json(pool), LoadBalancerPool.class);
            }
        }

        public class Update extends OpenStackRequest<LoadBalancerPool> {

            public Update(LoadBalancerPool pool) {
                super(CLIENT, HttpMethod.PUT, buildPath("lb/pools/", pool.getId()), Entity.json(pool),
                    LoadBalancerPool.class);
            }
        }

        public class Show extends OpenStackRequest<LoadBalancerPool> {

            public Show(String id) {
                super(CLIENT, HttpMethod.GET, buildPath("lb/pools/", id), null, LoadBalancerPool.class);
            }
        }

        public class Delete extends OpenStackRequest<Void> {

            public Delete(String id) {
                super(CLIENT, HttpMethod.DELETE, buildPath("lb/pools/", id), null, Void.class);
            }
        }

        public class AssociateMonitor extends OpenStackRequest<Void> {

            public AssociateMonitor(String poolId, AssociateHealthMonitor healthMonitor) {
                super(CLIENT, HttpMethod.POST,
                    new StringBuilder("lb/pools/").append(poolId).append("/health_monitors"), Entity
                        .json(healthMonitor), Void.class);
            }
        }

        public class DisassociateMonitor extends OpenStackRequest<Void> {

            public DisassociateMonitor(String poolId, String healthMonitorId) {
                super(CLIENT, HttpMethod.DELETE, new StringBuilder("lb/pools/").append(poolId)
                    .append("/health_monitors/").append(healthMonitorId), null, Void.class);
            }
        }
    }

    public class VIP {

        private final OpenStackClient CLIENT;

        public VIP(OpenStackClient client) {
            CLIENT = client;
        }

        public List list() {
            return new List();
        }

        public Create create(LoadBalancerVIP vip) {
            return new Create(vip);
        }

        public Update update(LoadBalancerVIP vip) {
            return new Update(vip);
        }

        public Delete delete(String vipId) {
            return new Delete(vipId);
        }

        public Show show(String vipId) {
            return new Show(vipId);
        }

        public class List extends OpenStackRequest<LoadBalancerVIPs> {

            public List() {
                super(CLIENT, HttpMethod.GET, "lb/vips", null, LoadBalancerVIPs.class);
            }
        }

        public class Query extends OpenStackRequest<LoadBalancerVIP> {

            public Query(LoadBalancerVIP vip) {
                // super(subnet);
                // target = target.path("v2.0").path("subnets");
                // target = queryParam(target);
                // return target.request(MediaType.APPLICATION_JSON).get(Subnets.class);
            }
        }

        public class Create extends OpenStackRequest<LoadBalancerVIP> {

            public Create(LoadBalancerVIP vip) {
                super(CLIENT, HttpMethod.POST, "lb/vips", Entity.json(vip), LoadBalancerVIP.class);
            }
        }

        public class Update extends OpenStackRequest<LoadBalancerVIP> {

            public Update(LoadBalancerVIP vip) {
                super(CLIENT, HttpMethod.PUT, buildPath("lb/vips/", vip.getId()), Entity.json(vip),
                    LoadBalancerVIP.class);
            }
        }

        public class Show extends OpenStackRequest<LoadBalancerVIP> {

            public Show(String id) {
                super(CLIENT, HttpMethod.GET, buildPath("lb/vips/", id), null, LoadBalancerVIP.class);
            }
        }

        public class Delete extends OpenStackRequest<Void> {

            public Delete(String id) {
                super(CLIENT, HttpMethod.DELETE, buildPath("lb/vips/", id), null, Void.class);
            }
        }
    }

}
