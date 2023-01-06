package com.vivek.api.service;


import java.util.List;

import com.vivek.api.dto.UserDetails;
import com.vivek.api.dto.UserDto;
import com.vivek.api.entity.User;

public interface UserService {
	UserDetails userSignup(UserDto user);
	UserDetails userLogin(UserDto usr);
	User deleteUser(int id);
	String deleteAllUser();
	User updateUser(UserDto usr);
	List<User> getAllUser();
	User getUserById(int id);
}
