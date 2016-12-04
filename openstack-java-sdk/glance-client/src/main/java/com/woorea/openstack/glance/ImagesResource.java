/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/


package com.woorea.openstack.glance;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import com.woorea.openstack.base.client.Entity;
import com.woorea.openstack.base.client.HttpMethod;
import com.woorea.openstack.base.client.OpenStackClient;
import com.woorea.openstack.base.client.OpenStackConnectException;
import com.woorea.openstack.base.client.OpenStackRequest;
import com.woorea.openstack.base.client.OpenStackResponse;
import com.woorea.openstack.base.client.OpenStackResponseException;
import com.woorea.openstack.glance.model.Image;
import com.woorea.openstack.glance.model.ImageDownload;
import com.woorea.openstack.glance.model.ImageUpload;
import com.woorea.openstack.glance.model.ImageMember;
import com.woorea.openstack.glance.model.ImageMembers;
import com.woorea.openstack.glance.model.Images;

public class ImagesResource {

    private final OpenStackClient CLIENT;

    public ImagesResource(OpenStackClient client) {
        CLIENT = client;
    }

    public List list(boolean detail) {
        return new List(detail);
    }

    public Create create(Image image) {
        return new Create(image);
    }

    public Show show(String id) {
        return new Show(id);
    }

    public Update update(String id, Image image) {
        return new Update(id, image);
    }

    public Delete delete(String id) {
        return new Delete(id);
    }

    public Upload upload(ImageUpload image) {
        return new Upload(image);
    }

    public Upload upload(String id, ImageUpload image) {
        return new Upload(id, image);
    }

    public Download download(String id) {
        return new Download(id);
    }

    public ListMembers listMembers(String id) {
        return new ListMembers(id);
    }

    public ReplaceMembers replaceMembers(String id, Collection<ImageMember> members) {
        return new ReplaceMembers(id, members);
    }

    public AddMember addMember(String id, String tenantId) {
        return new AddMember(id, tenantId);
    }

    public AddMember removeMember(String id, String tenantId) {
        return new AddMember(id, tenantId);
    }

    /**
     * List images using glance. The old nova api had a summary and detail capability, but glance did away with that.
     * For backward compatibility we are allowing the detail flag, but ignoring it
     */
    public class List extends OpenStackRequest<Images> {
        public List(boolean detail) {
            super(CLIENT, HttpMethod.GET, "/images", null, Images.class);
        }

    }

    public class Create extends OpenStackRequest<Image> {

        public Create(Image image) {
            super(CLIENT, HttpMethod.POST, "/images", null, Image.class);
            for (Map.Entry<String, String> entry : compose(image).entrySet()) {
                header(entry.getKey(), entry.getValue());
            }
        }

    }

    public class Update extends OpenStackRequest<Image> {

        public Update(String id, Image image) {
            super(CLIENT, HttpMethod.PUT, new StringBuilder("/images/").append(id).toString(), Entity.json(image),
                Image.class);
        }

    }

    public class Delete extends OpenStackRequest<Void> {

        public Delete(String id) {
            super(CLIENT, HttpMethod.DELETE, new StringBuilder("/images/").append(id).toString(), null, Void.class);
        }

    }

    /**
     * This class is used to "show" the details of a specific image
     */
    public class Show extends OpenStackRequest<Image> {

        /**
         * This method constructs the request to get the detail for a specific image.
         * <p>
         * This method was using HEAD in the past and has been changed to use GET. In order to use GET, the mapping of
         * the returned entity has to be specially handled (it is an unwrapped json object).
         * </p>
         * 
         * @param id
         *            The id of the image to show
         */
        public Show(String id) {
            // Changed from HEAD to GET
            super(CLIENT, HttpMethod.GET, new StringBuilder("/images/").append(id).toString(), null, Image.class);
        }

        /**
         * We have to override the parsing of the returned entity here because it comes back to us as an unwrapped json
         * object (i.e., there is no root class name). In order to do that, we have to intercept the response entity
         * processing, get the content as a generic JsonNode, and then map it ourself after setting the Jackson object
         * mapper to handle unwrapped objects.
         * 
         * @see com.woorea.openstack.base.client.OpenStackRequest#execute()
         */
        @Override
        public Image execute() throws OpenStackConnectException, OpenStackResponseException {
            OpenStackResponse response = CLIENT.request(this);
            JsonNode node = response.getEntity(JsonNode.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(Feature.WRAP_ROOT_VALUE, false);
            try {
                return mapper.readValue(node, Image.class);
            } catch (IOException e) {
                e.printStackTrace();
                throw new OpenStackResponseException(e.getMessage(), 500);
            }
        }

    }

    public class Upload extends OpenStackRequest<Image> {

        public Upload(String id, ImageUpload imageUpload) {
            super(CLIENT, HttpMethod.PUT, new StringBuilder("/images/").append(id).toString(), Entity
                .stream(imageUpload.getInputStream()), Image.class);
        }

        public Upload(ImageUpload imageUpload) {
            super(CLIENT, HttpMethod.POST, "/images", Entity.stream(imageUpload.getInputStream()), Image.class);

            for (Map.Entry<String, String> entry : compose(imageUpload.getImage()).entrySet()) {
                header(entry.getKey(), entry.getValue());
            }

            // file,s3,swift
            header("x-image-meta-store", imageUpload.getStore());
        }

    }

    public class Download extends OpenStackRequest<ImageDownload> {

        public Download(String id) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/images/").append(id).toString(), null,
                ImageDownload.class);
            header("Accept", "application/octet-stream");
        }

        /**
         * @see com.woorea.openstack.base.client.OpenStackRequest#execute()
         */
        @Override
        public ImageDownload execute() throws OpenStackConnectException, OpenStackResponseException {
            // custom parsing here
            OpenStackResponse response = CLIENT.request(this);
            ImageDownload imageDownload = new ImageDownload();
            imageDownload.setImage(parse(response.headers()));
            imageDownload.setInputStream(response.getInputStream());
            return imageDownload;
        }

    }

    public class ListMembers extends OpenStackRequest<ImageMembers> {

        public ListMembers(String id) {
            super(CLIENT, HttpMethod.GET, new StringBuilder("/images/").append(id).append("/members").toString(), null,
                ImageMembers.class);
        }

    }

    public class ReplaceMembers extends OpenStackRequest<Void> {

        public ReplaceMembers(String id, Collection<ImageMember> members) {
            super(CLIENT, HttpMethod.PUT, new StringBuilder("/images/").append(id).append("/members").toString(),
                Entity.json(new Memberships(members)), Void.class);
        }

    }

    public class AddMember extends OpenStackRequest<ImageMember> {

        public AddMember(String id, String tenantId) {
            super(CLIENT, HttpMethod.PUT, new StringBuilder("/images/").append(id).append("/members").append(tenantId)
                .toString(), null, ImageMember.class);
        }

    }

    public class RemoveMember extends OpenStackRequest<Void> {

        public RemoveMember(String id, String tenantId) {
            super(CLIENT, HttpMethod.DELETE, new StringBuilder("/images/").append(id).append("/members/")
                .append(tenantId).toString(), null, Void.class);
        }

    }

    public static Map<String, String> compose(Image image) {
        Map<String, String> headers = new HashMap<String, String>();

        headers.put("X-Image-Meta-Name", image.getName());
        headers.put("X-Image-Meta-Disk_format", image.getDiskFormat());
        headers.put("X-Image-Meta-Container_format", image.getContainerFormat());
        headers.put("X-Image-Meta-Id", image.getId());
        headers.put("X-Image-Meta-Size", (image.getSize() != null) ? image.getSize().toString() : null);
        headers.put("X-Image-Meta-Checksum", image.getChecksum());
        headers.put("X-Image-Meta-Is_public", String.valueOf(image.isPublic()));
        headers.put("X-Image-Meta-Owner", image.getOwner());

        for (String key : image.getProperties().keySet()) {
            image.getProperties().put("x-image-meta-property-" + key, image.getProperties().get(key));
        }

        return headers;
    }

    public static Image parse(Map<String, String> headers) {
        Image image = new Image();
        image.setId(headers.get("X-Image-Meta-Id"));
        image.setUri(headers.get("Location"));
        image.setName(headers.get("X-Image-Meta-Name"));
        image.setDiskFormat(headers.get("X-Image-Meta-Disk_format"));
        image.setContainerFormat(headers.get("X-Image-Meta-Container_format"));
        image.setSize(asLong(headers.get("X-Image-Meta-Size")));
        image.setChecksum(headers.get("X-Image-Meta-Checksum"));
        image.setCreatedAt(asCalendar(headers.get("X-Image-Meta-Created_at")));
        image.setUpdatedAt(asCalendar(headers.get("X-Image-Meta-Updated_at")));
        image.setDeletedAt(asCalendar(headers.get("X-Image-Meta-Deleted_at")));
        image.setDeleted(asBoolean(headers.get("X-Image-Meta-Deleted")));
        image.setStatus(headers.get("X-Image-Meta-Status"));
        image.setProtected(asBoolean(headers.get("X-Image-Meta-Protected")));
        image.setPublic(asBoolean(headers.get("X-Image-Meta-Is_public")));
        image.setMinRam(asInteger(headers.get("X-Image-Meta-Min_ram")));
        image.setMinDisk(asInteger(headers.get("X-Image-Meta-Min_disk")));
        image.setOwner(headers.get("X-Image-Meta-Owner"));
        for (String key : headers.keySet()) {
            if (key.startsWith("x-image-meta-property-")) {
                image.getProperties().put(key.substring(22), headers.get(key));
            }
        }
        return image;
    }

    private static Calendar asCalendar(String calendarString) {
        return Calendar.getInstance();
    }

    private static Integer asInteger(String integerString) {
        if (integerString != null) {
            return Integer.parseInt(integerString);
        }
        return 0;
    }

    private static Boolean asBoolean(String booleanString) {
        if (booleanString != null) {
            return Boolean.parseBoolean(booleanString);
        }
        return Boolean.FALSE;
    }

    private static Long asLong(String longString) {
        if (longString != null) {
            return Long.parseLong(longString);
        }
        return 0L;
    }

    public static class Memberships {

        @JsonProperty("memberships")
        private Collection<ImageMember> memberships;

        public Memberships(Collection<ImageMember> memberships) {
            this.memberships = memberships;
        }

    }

}
