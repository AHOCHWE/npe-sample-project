package com.mb;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Nationality {
	
	public List<String> citizenships = new ArrayList<String>();
	
	/**
     * No args constructor for use in serialization
     * 
     */
    public Nationality() {
    }

}
