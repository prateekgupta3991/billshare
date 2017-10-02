package com.share.bill.dto;

import com.share.bill.entities.User;

import java.io.Serializable;
import java.util.List;

public class GroupRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private List<User> userList ;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "GroupRequestDto{" +
				"name='" + name + '\'' +
				", userList=" + userList +
				'}';
	}
}
