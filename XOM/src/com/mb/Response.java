package com.mb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

	public String applicationCategory;
	public String fullName;
	public String fullNameWithNickname;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Response() {
	}
}
