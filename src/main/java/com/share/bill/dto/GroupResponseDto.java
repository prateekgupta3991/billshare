package com.share.bill.dto;

import com.share.bill.entities.User;

import java.io.Serializable;
import java.util.List;

public class GroupResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private List<UserResponseDto> users;
	private UserResponseDto admin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserResponseDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserResponseDto> users) {
		this.users = users;
	}

	public UserResponseDto getAdmin() {
		return admin;
	}

	public void setAdmin(UserResponseDto admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "GroupResponseDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", users=" + users +
				", admin=" + admin +
				'}';
	}
}
