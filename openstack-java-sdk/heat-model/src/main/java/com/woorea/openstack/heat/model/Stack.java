/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.heat.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonRootName;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Added outputs to stack</dd>
 * </dl>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("stack")
public class Stack {
    @JsonProperty("description")
    private String description;

    @JsonProperty("links")
    private List<Link> links;

    @JsonProperty("stack_status_reason")
    private String stackStatusReason;

    @JsonProperty("stack_name")
    private String stackName;

    @JsonProperty("updated_time")
    private Date updatedTime;

    @JsonProperty("creation_time")
    private Date creationTime;

    @JsonProperty("stack_status")
    private String stackStatus;

    @JsonProperty("id")
    private String id;
    
    // ObjectMapper instance to parse Json stack outputs
    @JsonIgnore
	private static ObjectMapper mapper = new ObjectMapper();

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getStackStatus() {
        return stackStatus;
    }

    public void setStackStatus(String stackStatus) {
        this.stackStatus = stackStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getStackName() {
        return stackName;
    }

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    public String getStackStatusReason() {
        return stackStatusReason;
    }

    public void setStackStatusReason(String stackStatusReason) {
        this.stackStatusReason = stackStatusReason;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "description='" + description + '\'' +
                ", links=" + links +
                ", stackStatusReason='" + stackStatusReason + '\'' +
                ", stackName='" + stackName + '\'' +
                ", updatedTime=" + updatedTime +
                ", creationTime=" + creationTime +
                ", stackStatus='" + stackStatus + '\'' +
                ", id='" + id + '\'' +
                ", outputs='" + outputs + '\'' +
                ", parameters='" + parameters + '\'' +
                '}';
    }
    
	@JsonIgnoreProperties(ignoreUnknown=true)
	public static final class Output {
	    @JsonProperty("output_value")
		private Object outputValue;
		
		private String description;
		
	    @JsonProperty("output_key")
		private String outputKey;
		
		public Object getOutputValue() {
			return outputValue;
		}

		public String getDescription() {
			return description;
		}

		public String getOutputKey() {
			return outputKey;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Output [key=" + outputKey + ", value="
					+ outputValue + "]";
		}
	}
	
	private List<Output> outputs;

	public List<Output> getOutputs() {
		return outputs;
	}
	
	private Object _findOutputValue (String key) {
		for (Output o : outputs) {
			if (o.getOutputKey().equals(key)) {
				return o.getOutputValue();
			}
		}
		return null;
	}
	
	/*
	 * Return a stack output as a String.
	 * Generally speaking, most outputs will be Strings.
	 */
	public String getOutputValue (String key)
	{
		Object value = _findOutputValue(key);
		if (value != null)
			return value.toString();
		else
			return null;
	}
	
	/*
	 * Return a stack output as a Json-mapped Object of the provided type.
	 * This is useful for json-object stack outputs.
	 */
	public <T> T getOutputValue (String key, Class<T> type)
	{
		try {
			String s = mapper.writeValueAsString(_findOutputValue(key));
			return (mapper.readValue(s, type));
		}
		catch (IOException e) {
			return null;
		}
	}
	
	@JsonProperty("parameters")
	private Map<String,Object> parameters = new HashMap<String,Object>();
	
	public void setParameters (Map<String,Object> params)
	{
		// Need to "fix" comma-delimited-list parameters for pre-Juno Heat
		// (see https://bugs.launchpad.net/heat/+bug/1367393)
		parameters = params;
		
		for (Entry<String,Object> param : parameters.entrySet())
		{
			// CDL params are returned as a string with format:
			// "[u'<value1>',u'<value2>',...]"
			String value = param.getValue().toString();
			if (value.startsWith("[") && value.endsWith("]"))
			{
				param.setValue(value.substring(1,value.length()-1).replaceAll("u'([^\']+)'","$1"));
			}
		}
	}
	
	public Map<String,Object> getParameters() {
		return parameters;
	}
}
