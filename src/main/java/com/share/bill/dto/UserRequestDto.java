package com.share.bill.dto;

import java.io.*;

public class UserRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;

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

	@Override
	public String toString() {
		return "UserRequestDto [name=" + name + ", email=" + email + "]";
	}
}
