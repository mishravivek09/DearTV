package com.vivek.api.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "userName"))
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name should not be empty")
	private String name;
	@Email(message = "please enter email")
	@NotBlank(message = "please enter username")
	private String userName;
	@NotBlank(message = "Please enter password")
	private String password;
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}




	public User(@NotBlank(message = "Name should not be empty") String name,
			@Email(message = "please enter email") @NotBlank(message = "please enter username") String userName,
			@NotBlank(message = "Please enter password") String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.password = password;
	}




	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password + "]";
	}

	
	
	
	
}
