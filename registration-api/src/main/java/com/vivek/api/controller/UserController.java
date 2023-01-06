package com.vivek.api.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.vivek.api.dto.UserDetails;
import com.vivek.api.dto.UserDto;
import com.vivek.api.entity.User;
import com.vivek.api.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class UserController {
	private UserService service;

	public UserController(UserService service) {
		super();
		this.service = service;
	}
	@GetMapping("/")
	public String getMessage() {
		return "Hello World !";
	}
	@PostMapping("/user/signup")
	public UserDetails userSignup(@Valid @RequestBody UserDto user) {
		return service.userSignup(user);
	}
	@PostMapping("/user/login")
	public UserDetails userLogin(@Valid @RequestBody UserDto usr) {
		return service.userLogin(usr);
	}
	@DeleteMapping("/user/remove/{id}")
	public User deleteById(@PathVariable int id) {
		return service.deleteUser(id);
	}
	@DeleteMapping("/user/remove/all")
	public String deleteAllUser() {
		return service.deleteAllUser();
	}
	@PutMapping("/user/update")
	public User updateUser(@Valid @RequestBody UserDto usr) {
		return service.updateUser(usr);
	}
	@GetMapping("/user/all")
	public List<User> getAllUser(){
		return service.getAllUser();
	}
	@GetMapping("/user/get/{id}")
	public User getById(@PathVariable int id) {
		return service.getUserById(id);
	}
}
