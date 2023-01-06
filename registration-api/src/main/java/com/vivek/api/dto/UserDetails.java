package com.vivek.api.dto;

public class UserDetails {
	private String name;
	private String userName;
	
	
	
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserDetails(String name, String userName) {
		super();
		this.name = name;
		this.userName = userName;
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
	
	
}
