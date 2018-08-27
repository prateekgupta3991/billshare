package com.share.bill.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String contact;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "UserRequestDto{" +
				"name='" + name + '\'' +
				", email='" + email + '\'' +
				", contact='" + contact + '\'' +
				'}';
	}
}
