package com.share.bill.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.*;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<String> userEmails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(List<String> userEmails) {
		this.userEmails = userEmails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GroupRequestDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", userEmails=" + userEmails +
				'}';
	}
}
