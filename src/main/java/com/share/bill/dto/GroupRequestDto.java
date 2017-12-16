package com.share.bill.dto;

import java.io.*;
import java.util.*;

public class GroupRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<UserRequestDto> userRequestDtoList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRequestDto> getUserRequestDtoList() {
		return userRequestDtoList;
	}

	public void setUserRequestDtoList(List<UserRequestDto> userRequestDtoList) {
		this.userRequestDtoList = userRequestDtoList;
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
				", userRequestDtoList=" + userRequestDtoList +
				'}';
	}
}
