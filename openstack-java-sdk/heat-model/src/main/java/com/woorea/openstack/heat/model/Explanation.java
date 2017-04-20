/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.heat.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/3/2015</dt>
 * <dd>Added new fields</dd>
 * </dl>
 */
// There is no Root element for the Explanation return
//@JsonRootName("error")
public class Explanation {
    @JsonProperty("explanation")
    private String explanation;

    @JsonProperty("code")
    private int code;

    @JsonProperty("title")
    private String title;
    
    @JsonRootName("error")
    public static class Error {
        @JsonProperty("message")
        private String message;

        @JsonProperty("traceback")
        private String traceback;

        @JsonProperty("type")
        private String type;

        public String getMessage() {
        	return message;
        }
        
        public String getTraceback() {
        	return traceback;
        }
        
        public String getType() {
        	return type;
        }
    }
 
    private Error error;
    
    public String getExplanation() {
    	return explanation;
    }
    
    public int getCode() {
    	return code;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public Error getError() {
    	return error;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Explanation [ " +
				"code='" + code +
				"', title='" + title +
				"', explanation='" + explanation +
				"', Error [type='" + error.type +
				"', message='" + error.message + "' ] ]";
	}

}
