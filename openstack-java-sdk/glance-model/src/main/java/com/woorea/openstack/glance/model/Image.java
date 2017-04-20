/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.glance.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonRootName("image")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements Serializable {

    private String checksum;

    @JsonProperty("clean_attempts")
    private Integer cleanAttempts;

    @JsonProperty("container_format")
    private String containerFormat;

    @JsonProperty("created_at")
    private Calendar createdAt;

    @JsonProperty("deleted_at")
    private Calendar deletedAt;

    @JsonProperty("disk_format")
    private String diskFormat;

    @JsonProperty("instance_type_ephemeral_gb")
    private Integer ephemeralGB;

    private String file;

    private String id;

    @JsonProperty("image_location")
    private String imageLocation;

    @JsonProperty("image_state")
    private String imageState;

    @JsonProperty("image_type")
    private String imageType;

    @JsonProperty("instance_type_flavorid")
    private String instanceFlavorId;

    @JsonProperty("instance_type_memory_mb")
    private Integer instanceMemoryMB;

    @JsonProperty("instance_type_name")
    private String instanceName;

    @JsonProperty("instance_type_root_gb")
    private Integer instanceRootGB;

    @JsonProperty("instance_type_id")
    private String instanceTypeId;

    @JsonProperty("instance_uuid")
    private String instanceUUID;

    @JsonProperty("instance_type_vcpus")
    private Integer instanceVcpus;

    @JsonProperty("deleted")
    private Boolean isDeleted;

    @JsonProperty("protected")
    private Boolean isProtected;

    @JsonProperty("is_public")
    private Boolean isPublic;

    @JsonProperty("min_disk")
    private Integer minDisk;

    @JsonProperty("min_ram")
    private Integer minRam;

    private String name;

    @JsonProperty("network_allocated")
    private String networkAllocated;
	
    private String owner;

    private Map<String, Object> properties;

    private String schema;

    private Long size;

    private String status;

    private List<String> tags;

    @JsonProperty("updated_at")
    private Calendar updatedAt;

    @JsonProperty("self")
    private String uri;

    @JsonProperty("user_id")
    private String userId;

    private String visibility;

    /**
     * @return the checksum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * @return the value of cleanAttempts
     */
    public Integer getCleanAttempts() {
        return cleanAttempts;
    }

    /**
     * @return the containerFormat
     */
    public String getContainerFormat() {
        return containerFormat;
    }

    /**
     * @return the createdAt
     */
    public Calendar getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the deletedAt
     */
    public Calendar getDeletedAt() {
        return deletedAt;
    }

    /**
     * @return the diskFormat
     */
    public String getDiskFormat() {
        return diskFormat;
    }

    /**
     * @return the value of ephemeralGB
     */
    public Integer getEphemeralGB() {
        return ephemeralGB;
    }

    /**
     * @return the value of file
     */
    public String getFile() {
        return file;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the value of imageLocation
     */
    public String getImageLocation() {
        return imageLocation;
    }

    /**
     * @return
     */
    public String getImageState() {
        return imageState;
    }

    /**
     * @return
     */
    public String getImageType() {
        return imageType;
    }

    /**
     * @return the value of instanceFlavorId
     */
    public String getInstanceFlavorId() {
        return instanceFlavorId;
    }

    /**
     * @return the value of instanceMemoryMB
     */
    public Integer getInstanceMemoryMB() {
        return instanceMemoryMB;
    }

    /**
     * @return the value of instanceName
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * @return the value of instanceRootGB
     */
    public Integer getInstanceRootGB() {
        return instanceRootGB;
    }

    /**
     * @return the value of instanceTypeId
     */
    public String getInstanceTypeId() {
        return instanceTypeId;
    }

    /**
     * @return the value of instanceUUID
     */
    public String getInstanceUUID() {
        return instanceUUID;
    }

    /**
     * @return the value of instanceVcpus
     */
    public Integer getInstanceVcpus() {
        return instanceVcpus;
    }

    /**
     * @return the minDisk
     */
    public Integer getMinDisk() {
        return minDisk;
    }

    /**
     * @return the minRam
     */
    public Integer getMinRam() {
        return minRam;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of networkAllocated
     */
    public String getNetworkAllocated() {
        return networkAllocated;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return the properties
     */
    public Map<String, Object> getProperties() {
        if (properties == null) {
            properties = new HashMap<String, Object>();
        }
        return properties;
    }

    /**
     * @return
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @return the updatedAt
     */
    public Calendar getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @return the value of userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return the value of visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @return the value of isDeleted
     */
    public Boolean isDeleted() {
        return isDeleted;
    }

    /**
     * @return the isProtected
     */
    public Boolean isProtected() {
        return isProtected;
    }

    /**
     * @return the value of isPublic
     */
    public Boolean isPublic() {
        return isPublic;
    }

    /**
     * @param checksum
     *            the checksum to set
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     * @param cleanAttempts
     *            the value for cleanAttempts
     */
    public void setCleanAttempts(Integer cleanAttempts) {
        this.cleanAttempts = cleanAttempts;
    }

    /**
     * @param containerFormat
     *            the containerFormat to set
     */
    public void setContainerFormat(String containerFormat) {
        this.containerFormat = containerFormat;
    }

    /**
     * @param createdAt
     *            the createdAt to set
     */
    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @param isDeleted
     *            the value for isDeleted
     */
    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @param deletedAt
     *            the deletedAt to set
     */
    public void setDeletedAt(Calendar deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * @param diskFormat
     *            the diskFormat to set
     */
    public void setDiskFormat(String diskFormat) {
        this.diskFormat = diskFormat;
    }

    /**
     * @param ephemeralGB
     *            the value for ephemeralGB
     */
    public void setEphemeralGB(Integer ephemeralGB) {
        this.ephemeralGB = ephemeralGB;
    }

    /**
     * @param file
     *            the value for file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param imageLocation
     *            the value for imageLocation
     */
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    /**
     * @param imageState
     */
    public void setImageState(String imageState) {
        this.imageState = imageState;
    }

    /**
     * @param imageType
     */
    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    /**
     * @param instanceFlavorId
     *            the value for instanceFlavorId
     */
    public void setInstanceFlavorId(String instanceFlavorId) {
        this.instanceFlavorId = instanceFlavorId;
    }

    /**
     * @param instanceMemoryMB
     *            the value for instanceMemoryMB
     */
    public void setInstanceMemoryMB(Integer instanceMemoryMB) {
        this.instanceMemoryMB = instanceMemoryMB;
    }

    /**
     * @param instanceName
     *            the value for instanceName
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * @param instanceRootGB
     *            the value for instanceRootGB
     */
    public void setInstanceRootGB(Integer instanceRootGB) {
        this.instanceRootGB = instanceRootGB;
    }

    /**
     * @param instanceTypeId
     *            the value for instanceTypeId
     */
    public void setInstanceTypeId(String instanceTypeId) {
        this.instanceTypeId = instanceTypeId;
    }

    /**
     * @param instanceUUID
     *            the value for instanceUUID
     */
    public void setInstanceUUID(String instanceUUID) {
        this.instanceUUID = instanceUUID;
    }

    /**
     * @param instanceVcpus
     *            the value for instanceVcpus
     */
    public void setInstanceVcpus(Integer instanceVcpus) {
        this.instanceVcpus = instanceVcpus;
    }

    /**
     * @param minDisk
     *            the minDisk to set
     */
    public void setMinDisk(Integer minDisk) {
        this.minDisk = minDisk;
    }

    /**
     * @param minRam
     *            the minRam to set
     */
    public void setMinRam(Integer minRam) {
        this.minRam = minRam;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param networkAllocated
     *            the value for networkAllocated
     */
    public void setNetworkAllocated(String networkAllocated) {
        this.networkAllocated = networkAllocated;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @param properties
     */
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * @param isProtected
     *            the isProtected to set
     */
    public void setProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    /**
     * @param isPublic
     *            the value for isPublic
     */
    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * @param schema
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param tags
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @param updatedAt
     *            the updatedAt to set
     */
    public void setUpdatedAt(Calendar updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @param uri
     *            the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @param userId
     *            the value for userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @param visibility
     *            the value for visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format(
            "Image [id{%s}, name{%s}, type{%s}, status{%s}, protected{%s}, visibility{%s}, container format{%s}, "
                + "disk format{%s}, size{%s}, min disk{%s}, min RAM{%s}, created{%s}, updated{%s}, URI{%s}, "
                + "instance [UUID{%s}, name{%s} flavor{%s}], tags{%s}, properties{%s}]", id, name, imageType, status,
            isProtected, visibility, containerFormat, diskFormat, size, minDisk, minRam, createdAt, updatedAt, uri,
            instanceUUID, instanceName, instanceFlavorId, tags, properties);
    }

}
