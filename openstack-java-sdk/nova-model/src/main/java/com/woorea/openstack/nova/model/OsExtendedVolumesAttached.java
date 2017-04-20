package com.woorea.openstack.nova.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("os-extended-volumes:volumes_attached")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OsExtendedVolumesAttached {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
