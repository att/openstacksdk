/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.api;

import java.util.Map;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.nova.model.InterfaceAttachment;
import com.woorea.openstack.nova.model.InterfaceAttachmentForCreate;
import com.woorea.openstack.nova.model.InterfaceAttachments;
import com.woorea.openstack.nova.model.Metadata;
import com.woorea.openstack.nova.model.Server;
import com.woorea.openstack.nova.model.Server.Addresses;
import com.woorea.openstack.nova.model.ServerAction;
import com.woorea.openstack.nova.model.ServerAction.AddSecurityGroup;
import com.woorea.openstack.nova.model.ServerAction.ChangePassword;
import com.woorea.openstack.nova.model.ServerAction.ConfirmResize;
import com.woorea.openstack.nova.model.ServerAction.ConsoleOutput;
import com.woorea.openstack.nova.model.ServerAction.CreateBackup;
import com.woorea.openstack.nova.model.ServerAction.CreateImage;
import com.woorea.openstack.nova.model.ServerAction.Evacuate;
import com.woorea.openstack.nova.model.ServerAction.GetConsoleOutput;
import com.woorea.openstack.nova.model.ServerAction.GetVncConsole;
import com.woorea.openstack.nova.model.ServerAction.Lock;
import com.woorea.openstack.nova.model.ServerAction.Pause;
import com.woorea.openstack.nova.model.ServerAction.Reboot;
import com.woorea.openstack.nova.model.ServerAction.Rebuild;
import com.woorea.openstack.nova.model.ServerAction.RemoveSecurityGroup;
import com.woorea.openstack.nova.model.ServerAction.Rescue;
import com.woorea.openstack.nova.model.ServerAction.Resize;
import com.woorea.openstack.nova.model.ServerAction.Resume;
import com.woorea.openstack.nova.model.ServerAction.RevertResize;
import com.woorea.openstack.nova.model.ServerAction.Start;
import com.woorea.openstack.nova.model.ServerAction.Stop;
import com.woorea.openstack.nova.model.ServerAction.Suspend;
import com.woorea.openstack.nova.model.ServerAction.Unlock;
import com.woorea.openstack.nova.model.ServerAction.Unpause;
import com.woorea.openstack.nova.model.ServerAction.Unrescue;
import com.woorea.openstack.nova.model.ServerAction.VncConsole;
import com.woorea.openstack.nova.model.ServerForCreate;
import com.woorea.openstack.nova.model.Servers;
import com.woorea.openstack.nova.model.VirtualInterfaces;
import com.woorea.openstack.nova.model.VolumeAttachment;
import com.woorea.openstack.nova.model.VolumeAttachments;

/**
 * This class is used to encapsulate all of the requests that can be made to the compute service to manipulate the state
 * of an instance.
 */
public class ServersResource {

    /**
     * A subset of the Compute service api consists of "actions" that can be performed against an existing instance.
     * These actions are used to manipulate the state of the existing instance, and do not create or destroy instances.
     * In all cases, the URI of the request is identical, with only the request and response entities (if any) changing.
     * Therefore, this class serves as a base class for all requests that are of type "Action" and supports the creation
     * of the URI portion of the request.
     * 
     * @param <T>
     *            The return entity type, or Void if no entity is returned.
     */
    public abstract class Action<T> extends OpenStackRequest<T> {

        /**
         * Create the action request by creating the URI, the request entity, and the return entity type.
         *
         * @param id
         *            The id of the server to which the action is to be applied
         * @param entity
         *            The request entity that defines the action to be performed
         * @param returnType
         *            The class of the type of return entity, or Void.class if none
         */
        public Action(String id, Entity<?> entity, Class<T> returnType) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(id).append("/action"), entity,
                returnType);
        }

    }

    public class AssociateFloatingIpAction extends Action<Void> {

        public AssociateFloatingIpAction(String id, ServerAction.AssociateFloatingIp action) {
            super(id, Entity.json(action), Void.class);
        }

    }

    public class AssociateSecurityGroupAction extends OpenStackRequest<Void> {

        public AssociateSecurityGroupAction(String id, AddSecurityGroup securityGroup) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(id).append("/action"), Entity
                .json(securityGroup), Void.class);
        }

    }

    public class AttachVolume extends OpenStackRequest<Void> {

        public AttachVolume(String serverId, final VolumeAttachment volumeAttachment) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(serverId).append(
                "/os-volume_attachments"), Entity.json(volumeAttachment), Void.class);
        }

    }

    public class Boot extends OpenStackRequest<Server> {

        private ServerForCreate server;

        public Boot(ServerForCreate server) {
            super(CLIENT, HttpMethod.POST, "/servers", Entity.json(server), Server.class);
            this.server = server;
        }

    }

    public class ChangePasswordAction extends Action<Server> {

        private ChangePassword action;

        public ChangePasswordAction(String id, ChangePassword action) {
            super(id, Entity.json(action), Server.class);
        }

    }

    public class ConfirmResizeAction extends Action<Void> {

        public ConfirmResizeAction(String id) {
            super(id, Entity.json(new ConfirmResize()), Void.class);
        }

    }

    public class CreateBackupAction extends Action<Void> {

        public CreateBackupAction(String id, CreateBackup action) {
            super(id, Entity.json(action), Void.class);
        }

    }

    public class CreateImageAction extends Action<Void> {

        public CreateImageAction(String id, CreateImage createImage) {
            super(id, Entity.json(createImage), Void.class);
        }

    }

    public class CreateInterfaceAttachment extends OpenStackRequest<InterfaceAttachment> {

        public CreateInterfaceAttachment(String serverId, InterfaceAttachmentForCreate action) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(serverId).append("/os-interface"),
                Entity.json(action), InterfaceAttachment.class);
        }

    }

    public class CreateOrUpdateMetadata extends OpenStackRequest<Metadata> {

        public CreateOrUpdateMetadata(String id, Metadata metadata) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(id).append("/metadata"), Entity
                .json(metadata), Metadata.class);
        }

    }

    public class Delete extends OpenStackRequest<Void> {

        public Delete(String id) {
            super(CLIENT, HttpMethod.DELETE, new StringBuilder("/servers/").append(id), null, Void.class);
        }

    }

    public class DeleteMetadata extends OpenStackRequest<Void> {

        public DeleteMetadata(String id, String key) {
            super(CLIENT, HttpMethod.DELETE,
                new StringBuilder("/servers/").append(id).append("/metadata/").append(key), null, Void.class);
        }

    }

    /**
     * Used to request the detachment of a port from a server.
     *
     * @since May 20, 2016
     * @version $Id$
     */
    public class DetachInterfaceAttachment extends OpenStackRequest<Void> {

        /**
         * Detach the server interface
         * 
         * @param serverId
         *            The server to be manipulated
         * @param portId
         *            The port id to be detached
         */
        public DetachInterfaceAttachment(String serverId, String portId) {
            super(CLIENT, HttpMethod.DELETE, new StringBuilder("/servers/").append(serverId).append("/os-interface/")
                .append(portId), null, Void.class);
        }

    }

    public class DetachVolume extends OpenStackRequest<Void> {

        public DetachVolume(String serverId, String volumeId) {
            super(CLIENT, HttpMethod.DELETE, new StringBuilder("/servers/").append(serverId)
                .append("/os-volume_attachments/").append(volumeId), null, Void.class);
        }

    }

    public class DisassociateFloatingIpAction extends Action<Void> {

        public DisassociateFloatingIpAction(String id, ServerAction.DisassociateFloatingIp action) {
            super(id, Entity.json(action), Void.class);
        }

    }

    public class DisassociateSecurityGroupAction extends OpenStackRequest<Void> {

        public DisassociateSecurityGroupAction(String id, RemoveSecurityGroup securityGroup) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(id).append("/action"), Entity
                .json(securityGroup), Void.class);
        }

    }

    /**
     * This class is used to request an evacuate of an instance, optionally to a specific target host
     * 
     * @author Dewayne Hafenstein
     * @since Dec 2, 2015
     */
    public class EvacuateAction extends Action<Void> {

        /**
         * Construct the request to evacuate the server
         * 
         * @param uuid
         *            The instance to be evacuated
         * @param entity
         *            The request entity
         */
        public EvacuateAction(String uuid, ServerAction.Evacuate entity) {
            super(uuid, Entity.json(entity), Void.class);
        }
    }

    public class GetConsoleOutputAction extends Action<ConsoleOutput> {

        public GetConsoleOutputAction(String id, GetConsoleOutput action) {
            super(id, Entity.json(action), ConsoleOutput.class);
        }

    }

    public class GetVncConsoleAction extends Action<VncConsole> {

        public GetVncConsoleAction(String id, GetVncConsole action) {
            super(id, Entity.json(action), VncConsole.class);
        }

    }

    public class List extends OpenStackRequest<Servers> {

        public List(boolean detail) {
            super(CLIENT, HttpMethod.GET, detail ? "/servers/detail" : "/servers", null, Servers.class);
        }

    }

    public class ListInterfaceAttachments extends OpenStackRequest<InterfaceAttachments> {

        public ListInterfaceAttachments(String serverId) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(serverId).append("/os-interface"),
                null, InterfaceAttachments.class);
        }

    }

    /**
     * This class is used to list the virtual interfaces of a server.
     *
     * @author Dewayne Hafenstein
     * @since Sep 14, 2015
     */
    public class ListVirtualInterfaces extends OpenStackRequest<VirtualInterfaces> {

        public ListVirtualInterfaces(String serverId) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(serverId).append(
                "/os-virtual-interface"), null, VirtualInterfaces.class);
        }

    }

    public class ListVolumeAttachments extends OpenStackRequest<VolumeAttachments> {

        public ListVolumeAttachments(String serverId) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(serverId).append(
                "/os-volume_attachments"), null, VolumeAttachments.class);
        }

    }

    public class LockAction extends Action<Void> {

        public LockAction(String id) {
            super(id, Entity.json(new Lock()), Void.class);
        }

    }

    /**
     * This class is used to request migration of an instance. Normally this api requires administrator access rights.
     * Attempting to use this api with a user that does not have those rights will return an exception.
     *
     * @author Dewayne Hafenstein
     * @since Dec 2, 2015
     */
    public class MigrateAction extends Action<Void> {

        public MigrateAction(String id) {
            super(id, Entity.json(new ServerAction.Migrate()), Void.class);
        }

    }

    public class PauseAction extends Action<Void> {

        public PauseAction(String id) {
            super(id, Entity.json(new Pause()), Void.class);
        }

    }

    public class RebootAction extends Action<Void> {

        private Reboot action;

        public RebootAction(String id, Reboot action) {
            super(id, Entity.json(action), Void.class);
        }

    }

    public class RebuildAction extends Action<Server> {

        private Rebuild action;

        public RebuildAction(String id, Rebuild action) {
            super(id, Entity.json(action), Server.class);
        }

    }

    public class ReplaceMetadata extends OpenStackRequest<Metadata> {

        public ReplaceMetadata(String id, Metadata metadata) {
            super(CLIENT, HttpMethod.PUT, new StringBuilder("/servers/").append(id).append("/metadata"), Entity
                .json(metadata), Metadata.class);
        }

    }

    public class RescueServer extends OpenStackRequest<Void> {

        public RescueServer(String id, Rescue action) {
            super(CLIENT, HttpMethod.POST, new StringBuilder("/servers/").append(id).append("/action"), Entity
                .json(action), Void.class);
        }

    }

    public class ResizeAction extends Action<Void> {

        private Resize action;

        public ResizeAction(String id, Resize action) {
            super(id, Entity.json(action), Void.class);
        }

    }

    public class ResumeAction extends Action<Void> {

        public ResumeAction(String id) {
            super(id, Entity.json(new Resume()), Void.class);
        }

    }

    public class RevertResizeAction extends Action<Server> {

        public RevertResizeAction(String id) {
            super(id, Entity.json(new RevertResize()), Server.class);
        }

    }

    public class Show extends OpenStackRequest<Server> {

        public Show(String id) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(id), null, Server.class);
        }

    }

    public class ShowInterfaceAttachment extends OpenStackRequest<InterfaceAttachment> {

        public ShowInterfaceAttachment(String serverId, String interfaceAttachmentId) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(serverId).append("/os-interface/")
                .append(interfaceAttachmentId), null, InterfaceAttachment.class);
        }

    }

    public class ShowMetadata extends OpenStackRequest<Metadata> {

        public ShowMetadata(String id) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(id).append("/metadata"), null,
                Metadata.class);
        }

    }

    public class ShowServerAddresses extends OpenStackRequest<Addresses> {

        public ShowServerAddresses(String id) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(id).append("/ips"), null,
                Addresses.class);
        }

    }

    public class ShowVolumeAttachment extends OpenStackRequest<VolumeAttachment> {

        public ShowVolumeAttachment(String serverId, String volumeAttachmentId) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/servers/").append(serverId)
                .append("/os-volume_attachments/").append(volumeAttachmentId), null, VolumeAttachment.class);
        }

    }

    public class StartAction extends Action<Void> {

        public StartAction(String id) {
            super(id, Entity.json(new Start()), Void.class);
        }

    }

    public class StopAction extends Action<Void> {

        public StopAction(String id) {
            super(id, Entity.json(new Stop()), Void.class);
        }

    }

    public class SuspendAction extends Action<Void> {

        public SuspendAction(String id) {
            super(id, Entity.json(new Suspend()), Void.class);
        }

    }

    public class UnlockAction extends Action<Void> {

        public UnlockAction(String id) {
            super(id, Entity.json(new Unlock()), Void.class);
        }

    }

    public class UnpauseAction extends Action<Void> {

        public UnpauseAction(String id) {
            super(id, Entity.json(new Unpause()), Void.class);
        }

    }

    public class UnrescueAction extends Action<Void> {

        public UnrescueAction(String id) {
            super(id, Entity.json(new Unrescue()), Void.class);
        }

    }

    public class UpdateServer extends OpenStackRequest<Server> {

        private Server server;

        public UpdateServer(String id, Server server) {
            super(CLIENT, HttpMethod.PUT, new StringBuilder("/servers/").append(id), Entity.json(server), Server.class);
            this.server = server;
        }

    }

    private final OpenStackClient CLIENT;

    public ServersResource(OpenStackClient client) {
        CLIENT = client;
    }

    public AssociateFloatingIpAction associateFloatingIp(String serverId, String floatingIpAddress) {
        com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp action =
            new com.woorea.openstack.nova.model.ServerAction.AssociateFloatingIp(floatingIpAddress);
        return new AssociateFloatingIpAction(serverId, action);
    }

    public AssociateSecurityGroupAction associateSecurityGroup(String serverId, String securityGroupName) {
        AddSecurityGroup securityGroup = new AddSecurityGroup();
        securityGroup.setName(securityGroupName);
        return new AssociateSecurityGroupAction(serverId, securityGroup);
    }

    public AttachVolume attachVolume(String serverId, String volumeId, String device) {
        VolumeAttachment volumeAttachment = new VolumeAttachment();
        volumeAttachment.setVolumeId(volumeId);
        volumeAttachment.setDevice(device);
        return new AttachVolume(serverId, volumeAttachment);
    }

    public Boot boot(ServerForCreate server) {
        return new Boot(server);
    }

    public ChangePasswordAction changePassword(String serverId, String adminPass) {
        ChangePassword changePassword = new ChangePassword();
        changePassword.setAdminPass(adminPass);
        return new ChangePasswordAction(serverId, changePassword);
    }

    public ConfirmResizeAction confirmResize(String serverId) {
        return new ConfirmResizeAction(serverId);
    }

    public CreateBackupAction createBackup(String serverId, CreateBackup action) {
        return new CreateBackupAction(serverId, action);
    }

    public CreateImageAction createImage(String serverId, String name, Map<String, String> metadata) {
        CreateImage createImage = new CreateImage();
        createImage.setName(name);
        createImage.setMetadata(metadata);
        return new CreateImageAction(serverId, createImage);
    }

    public CreateInterfaceAttachment createInterfaceAttachment(String serverId,
        InterfaceAttachmentForCreate interfaceAttachmentForCreate) {
        return new CreateInterfaceAttachment(serverId, interfaceAttachmentForCreate);
    }

    public CreateOrUpdateMetadata createOrUpdateMetadata(String id, Metadata metadata) {
        return new CreateOrUpdateMetadata(id, metadata);
    }

    public Delete delete(String id) {
        return new Delete(id);
    }

    public DeleteMetadata deleteMetadata(String id, String key) {
        return new DeleteMetadata(id, key);
    }

    public DetachVolume detachVolume(String serverId, String volumeId) {
        return new DetachVolume(serverId, volumeId);
    }

    /**
     * Detaches a port (interface) from a server
     * 
     * @param serverId
     *            The server to which the port is attached
     * @param portId
     *            The port to be detached from the server
     * @return The object that performs the detach
     */
    public DetachInterfaceAttachment detachInterfaceAttachment(String serverId, String portId) {
        return new DetachInterfaceAttachment(serverId, portId);
    }

    public DisassociateFloatingIpAction disassociateFloatingIp(String serverId, String floatingIpAddress) {
        com.woorea.openstack.nova.model.ServerAction.DisassociateFloatingIp action =
            new com.woorea.openstack.nova.model.ServerAction.DisassociateFloatingIp(floatingIpAddress);
        return new DisassociateFloatingIpAction(serverId, action);
    }

    public DisassociateSecurityGroupAction disassociateSecurityGroup(String serverId, String securityGroupName) {
        RemoveSecurityGroup securityGroup = new RemoveSecurityGroup();
        securityGroup.setName(securityGroupName);
        return new DisassociateSecurityGroupAction(serverId, securityGroup);
    }

    /**
     * Evacuate the server
     * 
     * @param serverId
     *            The server to be evacuated
     * @return The evacuate action to be performed
     * @see #evacuate(String, String, String, Boolean)
     */
    public EvacuateAction evacuate(String serverId) {
        return evacuate(serverId, null, null, null);
    }

    /**
     * Evacuates the server to a new host. The caller can supply the host name or id.
     * 
     * @param serverId
     *            The server to be evacuated
     * @param host
     *            The host name or ID of the target host (where the server is to be moved to).
     * @return The action to be performed
     * @see #evacuate(String, String, String, Boolean)
     */
    public EvacuateAction evacuate(String serverId, String host) {
        return evacuate(serverId, host, null, null);
    }

    /**
     * Evacuates the server to a new host, supplying a new administration password (optional) and allowing specification
     * of the use of shared storage.
     * 
     * @param serverId
     *            The id (UUID) of the server to be evacuated.
     * @param host
     *            The host name or ID of the new physical host for the server. This is the host that the server is to be
     *            moved to.
     * @param adminPassword
     *            An optional administration password. If null, the admin password is not set.
     * @param sharedStorage
     *            True if the server currently resides on shared storage. If null, the default (false) is used.
     * @return The action to be performed.
     */
    public EvacuateAction evacuate(String serverId, String host, String adminPassword, Boolean sharedStorage) {
        Evacuate entity = new Evacuate();
        entity.setAdminPassword(host);
        if (adminPassword != null && adminPassword.length() > 0) {
            entity.setAdminPassword(adminPassword);
        }
        if (sharedStorage != null) {
            entity.setSharedStorage(sharedStorage.booleanValue());
        } else {
            entity.setSharedStorage(false);
        }
        return new EvacuateAction(serverId, entity);
    }

    public GetConsoleOutputAction getConsoleOutput(String id, int length) {
        GetConsoleOutput action = new GetConsoleOutput(length);
        return new GetConsoleOutputAction(id, action);
    }

    public GetVncConsoleAction getVncConsole(String id, String type) {
        GetVncConsole action = new GetVncConsole(type);
        return new GetVncConsoleAction(id, action);
    }

    public List list(boolean detail) {
        return new List(detail);
    }

    public ListInterfaceAttachments listInterfaceAttachments(String serverId) {
        return new ListInterfaceAttachments(serverId);
    }

    /**
     * List the virtual interfaces for a specified server
     * 
     * @param serverId
     *            The server to list the virtual interfaces
     * @return The request object to list virtual interfaces
     */
    public ListVirtualInterfaces listVirtualInterfaces(String serverId) {
        return new ListVirtualInterfaces(serverId);
    }

    public ListVolumeAttachments listVolumeAttachments(String serverId) {
        return new ListVolumeAttachments(serverId);
    }

    public LockAction lock(String serverId) {
        return new LockAction(serverId);
    }

    /**
     * Creates an action that requests the server indicated be migrated.
     * <p>
     * This api request is normally restricted to users that have administration rights. Cloud providers can change
     * these permissions through the policy.json file.
     * </p>
     * 
     * @param serverId
     *            The server to be migrated
     * @return The action that requests the migration
     */
    public MigrateAction migrate(String serverId) {
        return new MigrateAction(serverId);
    }

    public PauseAction pause(String serverId) {
        return new PauseAction(serverId);
    }

    public RebootAction reboot(String serverId, String rebootType) {
        Reboot reboot = new Reboot();
        reboot.setType(rebootType);
        return new RebootAction(serverId, reboot);
    }

    public RebuildAction rebuild(String serverId, Rebuild rebuild) {
        return new RebuildAction(serverId, rebuild);
    }

    public ReplaceMetadata replaceMetadata(String id, Metadata metadata) {
        return new ReplaceMetadata(id, metadata);
    }

    public RescueServer rescue(String serverId, String adminPass) {
        Rescue action = new Rescue(adminPass);
        return new RescueServer(serverId, action);
    }

    public ResizeAction resize(String serverId, String flavorId, String diskConfig) {
        Resize resize = new Resize();
        resize.setFlavorRef(flavorId);
        resize.setDiskConfig(diskConfig);
        return new ResizeAction(serverId, resize);
    }

    public ResumeAction resume(String serverId) {
        return new ResumeAction(serverId);
    }

    public RevertResizeAction revertResize(String serverId) {
        return new RevertResizeAction(serverId);
    }

    public Show show(String id) {
        return new Show(id);
    }

    public ShowInterfaceAttachment showInterfaceAttachment(String serverId, String interfaceAttachmentId) {
        return new ShowInterfaceAttachment(serverId, interfaceAttachmentId);
    }

    public ShowMetadata showMetadata(String id) {
        return new ShowMetadata(id);
    }

    public ShowVolumeAttachment showVolumeAttachment(String serverId, String volumeAttachmentId) {
        return new ShowVolumeAttachment(serverId, volumeAttachmentId);
    }

    public StartAction start(String id) {
        return new StartAction(id);
    }

    public StopAction stop(String id) {
        return new StopAction(id);
    }

    public SuspendAction suspend(String serverId) {
        return new SuspendAction(serverId);
    }

    public UnlockAction unlock(String serverId) {
        return new UnlockAction(serverId);
    }

    public UnpauseAction unpause(String serverId) {
        return new UnpauseAction(serverId);
    }

    public UnrescueAction unrescue(String serverId) {
        return new UnrescueAction(serverId);
    }

    public UpdateServer update(String serverId, String name, String accessIPv4, String accessIPv6) {
        Server server = new Server();
        // server.setName(name);
        // server.setAccessIPv4(accessIPv4);
        // server.setAccessIPv6(accessIPv6);
        return new UpdateServer(serverId, server);
    }

}
