/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.heat;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.heat.model.CreateStackParam;
import com.woorea.openstack.heat.model.Stack;
import com.woorea.openstack.heat.model.Stacks;
import com.woorea.openstack.heat.model.UpdateStackParam;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Add ability to update a stack; added ability to delete a stack by name and ID; added ability to get a stack by
 * name and id.</dd>
 * </dl>
 */
public class StackResource {

    private final OpenStackClient client;

    public StackResource(OpenStackClient client) {
        this.client = client;
    }

    public CreateStack create(CreateStackParam param) {
        return new CreateStack(param);
    }

    public UpdateStack update(String name, UpdateStackParam param) {
        return new UpdateStack(name, param);
    }

    public List list() {
        return new List();
    }

    public GetStack byName(String name) {
        return new GetStack(name);
    }

    public GetStack get(String name, String id) {
        return new GetStack(name, id);
    }

    public DeleteStack deleteByName(String name) {
        return new DeleteStack(name);
    }

    public DeleteStack delete(String name, String id) {
        return new DeleteStack(name, id);
    }

    public class CreateStack extends OpenStackRequest<Stack> {
        public CreateStack(CreateStackParam params) {
            super(client, HttpMethod.POST, "/stacks", Entity.json(params), Stack.class);
        }
    }

    public class UpdateStack extends OpenStackRequest<Void> {
        public UpdateStack(String name, UpdateStackParam params) {
            super(client, HttpMethod.PUT, "/stacks/" + name, Entity.json(params), Void.class);
        }
    }

    public class DeleteStack extends OpenStackRequest<Void> {
        public DeleteStack(String name) {
            super(client, HttpMethod.DELETE, "/stacks/" + name, null, Void.class);
        }

        public DeleteStack(String name, String id) {
            super(client, HttpMethod.DELETE, "/stacks/" + name + "/" + id, null, Void.class);
        }
    }

    public class GetStack extends OpenStackRequest<Stack> {
        public GetStack(String name) {
            super(client, HttpMethod.GET, "/stacks/" + name, null, Stack.class);
        }

        public GetStack(String name, String id) {
            super(client, HttpMethod.GET, "/stacks/" + name + "/" + id, null, Stack.class);
        }
    }

    public class List extends OpenStackRequest<Stacks> {
        public List() {
            super(client, HttpMethod.GET, "/stacks", null, Stacks.class);
        }
    }

}
