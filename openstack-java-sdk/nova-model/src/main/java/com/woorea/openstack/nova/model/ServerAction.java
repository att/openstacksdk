/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.nova.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>08-04-2015 </dt>
 * <dd>Added AddSecurityGroup and RemoveSecurityGroup to actions.</dd>
 * </dl>
 */
public interface ServerAction extends Serializable {

    /**
     * This action was added to request migration of the target server
     *
     * @since Nov 23, 2015
     * @version $Id$
     */
    @JsonRootName("migrate")
    public static final class Migrate implements ServerAction {
        private static final long serialVersionUID = 1L;

    }

    /**
     * This action was added to allow the woorea library to support evacuate
     * 
     * @since Nov 23, 2015
     * @version $Id$
     */
    @JsonRootName("evacuate")
    public static final class Evacuate implements ServerAction {

        private static final long serialVersionUID = 1L;

        /**
         * The host to evacuate "to" (the target)
         */
        private String host;

        /**
         * An optional new administrator password
         */
        @JsonProperty("admin_password")
        private String adminPassword;

        /**
         * True if the instance being evacuated is located on shared storage
         */
        @JsonProperty("on_shared_storage")
        private boolean sharedStorage;

        /**
         * @return The target host to evacuate the instance to
         */
        public String getHost() {
            return host;
        }

        /**
         * @param host
         *            The target host to evacuate to
         */
        public void setHost(String host) {
            this.host = host;
        }

        /**
         * @return The new administrator password
         */
        public String getAdminPassword() {
            return adminPassword;
        }

        /**
         * @param adminPassword
         *            The new administrator password
         */
        public void setAdminPassword(String adminPassword) {
            this.adminPassword = adminPassword;
        }

        /**
         * @return True if the instance is located on shared storage
         */
        public boolean isSharedStorage() {
            return sharedStorage;
        }

        /**
         * @param sharedStorage
         *            True if the instance is located on shared storage
         */
        public void setSharedStorage(boolean sharedStorage) {
            this.sharedStorage = sharedStorage;
        }
    }

    /**
     *
     */
    @JsonRootName("changePassword")
    public static final class ChangePassword implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String adminPass;

        /**
         * 
         */
        public ChangePassword() {
            super();
        }

        /**
         * @param adminPass
         *            the administrator password
         */
        public ChangePassword(String adminPass) {
            this.adminPass = adminPass;
        }

        /**
         * @return the adminPass
         */
        public String getAdminPass() {
            return adminPass;
        }

        /**
         * @param adminPass
         *            the adminPass to set
         */
        public void setAdminPass(String adminPass) {
            this.adminPass = adminPass;
        }

    }

    /**
     *
     */
    @JsonRootName("reboot")
    public static final class Reboot implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String type;

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("rebuild")
    public static final class Rebuild implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String imageRef;

        private String name;

        private String adminPass;

        private String accessIPv4;

        private String accessIPv6;

        private Map<String, String> metadata /* = new HashMap<>() */;

        private List<PersonalityFile> personality /* = new ArrayList<>() */;

        @JsonProperty("OS-DCF:diskConfig")
        private String diskConfig;

        /**
         * @return the imageRef
         */
        public String getImageRef() {
            return imageRef;
        }

        /**
         * @param imageRef
         *            the imageRef to set
         */
        public void setImageRef(String imageRef) {
            this.imageRef = imageRef;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the adminPass
         */
        public String getAdminPass() {
            return adminPass;
        }

        /**
         * @param adminPass
         *            the adminPass to set
         */
        public void setAdminPass(String adminPass) {
            this.adminPass = adminPass;
        }

        /**
         * @return the accessIPv4
         */
        public String getAccessIPv4() {
            return accessIPv4;
        }

        /**
         * @param accessIPv4
         *            the accessIPv4 to set
         */
        public void setAccessIPv4(String accessIPv4) {
            this.accessIPv4 = accessIPv4;
        }

        /**
         * @return the accessIPv6
         */
        public String getAccessIPv6() {
            return accessIPv6;
        }

        /**
         * @param accessIPv6
         *            the accessIPv6 to set
         */
        public void setAccessIPv6(String accessIPv6) {
            this.accessIPv6 = accessIPv6;
        }

        /**
         * @return the metadata
         */
        public Map<String, String> getMetadata() {
            return metadata;
        }

        /**
         * @param metadata
         *            the metadata to set
         */
        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

        /**
         * @return the personality
         */
        public List<PersonalityFile> getPersonality() {
            return personality;
        }

        /**
         * @param personality
         *            the personality to set
         */
        public void setPersonality(List<PersonalityFile> personality) {
            this.personality = personality;
        }

        /**
         * @return the diskConfig
         */
        public String getDiskConfig() {
            return diskConfig;
        }

        /**
         * @param diskConfig
         *            the diskConfig to set
         */
        public void setDiskConfig(String diskConfig) {
            this.diskConfig = diskConfig;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("resize")
    public static final class Resize implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String flavorRef;

        @JsonProperty("OS-DCF:diskConfig")
        private String diskConfig;

        /**
         * @return the flavorRef
         */
        public String getFlavorRef() {
            return flavorRef;
        }

        /**
         * @param flavorRef
         *            the flavorRef to set
         */
        public void setFlavorRef(String flavorRef) {
            this.flavorRef = flavorRef;
        }

        /**
         * @return the diskConfig
         */
        public String getDiskConfig() {
            return diskConfig;
        }

        /**
         * @param diskConfig
         *            the diskConfig to set
         */
        public void setDiskConfig(String diskConfig) {
            this.diskConfig = diskConfig;
        }

    }

    /**
     *
     */
    @JsonRootName("confirmResize")
    public static final class ConfirmResize implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("revertResize")
    public static final class RevertResize implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("createImage")
    public static final class CreateImage implements ServerAction {
        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String name;

        private Map<String, String> metadata;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the metadata
         */
        public Map<String, String> getMetadata() {
            return metadata;
        }

        /**
         * @param metadata
         *            the metadata to set
         */
        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }
    }

    /**
     * 
     *
     */
    @JsonRootName("rescue")
    public static final class Rescue implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String adminPass;

        /**
         * 
         */
        public Rescue() {
            // nop
        }

        /**
         * @param adminPass
         *            Administrator password
         */
        public Rescue(String adminPass) {
            this.adminPass = adminPass;
        }

        /**
         * @return the adminPass
         */
        public String getAdminPass() {
            return adminPass;
        }

        /**
         * @param adminPass
         *            the adminPass to set
         */
        public void setAdminPass(String adminPass) {
            this.adminPass = adminPass;
        }

    }

    /**
     * 
     *
     */
    public static final class RescueResponse implements ServerAction {
        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String adminPass;

        /**
         * @return the adminPass
         */
        public String getAdminPass() {
            return adminPass;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("unrescue")
    public static final class Unrescue implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

    }

    /**
     * 
     *
     */
    @JsonRootName("unpause")
    public static final class Unpause implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("pause")
    public static final class Pause implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("suspend")
    public static final class Suspend implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("resume")
    public static final class Resume implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("lock")
    public static final class Lock implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("unlock")
    public static final class Unlock implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("os-getConsoleOutput")
    public static final class GetConsoleOutput implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private Integer length;

        /**
        * 
        */
        public GetConsoleOutput() {

        }

        /**
         * @param length
         *            the buffer size ??
         */
        public GetConsoleOutput(Integer length) {
            this.length = length;
        }

        /**
         * @return the length
         */
        public Integer getLength() {
            return length;
        }

        /**
         * @param length
         *            the length to set
         */
        public void setLength(Integer length) {
            this.length = length;
        }

    }

    /**
     * 
     *
     */
    public static final class ConsoleOutput implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String output;

        /**
         * @return the output
         */
        public String getOutput() {
            return output;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("os-getVNCConsole")
    public static final class GetVncConsole implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String type;

        /**
         * 
         */
        public GetVncConsole() {
            super();
        }

        /**
         * @param type
         *            ??
         */
        public GetVncConsole(String type) {
            super();
            this.type = type;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("console")
    public static final class VncConsole implements ServerAction {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String type;

        private String url;

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @return the url
         */
        public String getUrl() {
            return url;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("os-start")
    public static final class Start implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("os-stop")
    public static final class Stop implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("forceDelete")
    public static final class ForceDelete implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;
    }

    /**
     * 
     *
     */
    @JsonRootName("restore")
    public static final class Restore implements ServerAction {
        /**
        *
        */
        private static final long serialVersionUID = 1L;

    }

    /**
     * 
     *
     */
    @JsonRootName("addFloatingIp")
    public static final class AssociateFloatingIp implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String address;

        /**
         * 
         */
        public AssociateFloatingIp() {
            super();
        }

        /**
         * @param address
         *            The ip address
         */
        public AssociateFloatingIp(String address) {
            super();
            this.address = address;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address
         *            the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("removeFloatingIp")
    public static final class DisassociateFloatingIp implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String address;

        /**
        * 
        */
        public DisassociateFloatingIp() {
            super();
        }

        /**
         * @param address
         *            The ip address
         */
        public DisassociateFloatingIp(String address) {
            super();
            this.address = address;
        }

        /**
         * @return the address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address
         *            the address to set
         */
        public void setAddress(String address) {
            this.address = address;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("createBackup")
    public static final class CreateBackup implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String name;

        @JsonProperty("backup_type")
        private String type;

        private String rotation;

        private Map<String, String> metadata;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type
         *            the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return the rotation
         */
        public String getRotation() {
            return rotation;
        }

        /**
         * @param rotation
         *            the rotation to set
         */
        public void setRotation(String rotation) {
            this.rotation = rotation;
        }

        /**
         * @return the metadata
         */
        public Map<String, String> getMetadata() {
            return metadata;
        }

        /**
         * @param metadata
         *            the metadata to set
         */
        public void setMetadata(Map<String, String> metadata) {
            this.metadata = metadata;
        }

    }

    /**
     * 
     *
     */
    @JsonRootName("addSecurityGroup")
    public static final class AddSecurityGroup implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String name;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 
     *
     */
    @JsonRootName("removeSecurityGroup")
    public static final class RemoveSecurityGroup implements ServerAction {

        /**
        *
        */
        private static final long serialVersionUID = 1L;

        private String name;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }

}
