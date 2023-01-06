package com.vivek.api.dto;

import javax.validation.constraints.Email;

public class UserDto {
	private String name;
	@Email(message = "please enter email")
	private String userName;
	private String password;
	
	
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserDto(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "UserDto [name=" + name + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
