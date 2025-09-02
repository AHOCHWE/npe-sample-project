package com.mb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Request {

	public String applicationCategory;
	public Person person;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Request() {
	}
}
