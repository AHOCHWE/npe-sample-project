package com.mb;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Person {
	public String firstName;
	public String lastName;
	public List<String> nickNames = new ArrayList<String>();
	public List<LocalName> localNames = new ArrayList<LocalName>();

	public IndividualDisclosure individualDisclosure;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Person() {
	}
}
