package com.vivek.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vivek.api.dto.UserDetails;
import com.vivek.api.dto.UserDto;
import com.vivek.api.entity.User;
import com.vivek.api.exception.UserException;
import com.vivek.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public UserDetails userSignup(UserDto user) {
		User usr=repository.findByUserName(user.getUserName());
		if(usr!=null) {
			throw new UserException("User already registered..");
		}
		User users=new User(user.getName(),user.getUserName(),user.getPassword());
		UserDetails ud=new UserDetails(user.getName(),user.getUserName());
		repository.save(users);
		return ud;
	}

	@Override
	public UserDetails userLogin(UserDto usr) {
		User user=repository.findByUserName(usr.getUserName());
		if(user==null) {
			throw new UserException("User not registered..");
		}else {
			User res=repository.findByUserNameOrPassword(usr.getUserName(), usr.getPassword());
			if(res==null) {
				throw new UserException("Password is incorrect");
			}
			UserDetails ud=new UserDetails(res.getName(),res.getUserName());
			return ud;
		}
	}

	@Override
	public User deleteUser(int id) {
		Optional<User> opt=repository.findById(id);
		if(!opt.isPresent()) {
			throw new UserException("User not found");
		}
		User usr=opt.get();
		repository.delete(usr);
		return usr;
	}

	@Override
	public String deleteAllUser() {
		List<User> list=repository.findAll();
		if(list.isEmpty()) {
			throw new UserException("Users not exist");
		}
		repository.deleteAll();
		return "All user deleted";
	}

	@Override
	public User updateUser(UserDto usr) {
		User user=repository.findByUserName(usr.getUserName());
		if(user==null) {
			throw new UserException("User not found");
		}
		user.setPassword(usr.getPassword());
		return repository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> list=repository.findAll();
		if(list.isEmpty()) {
			throw new UserException("Users not found");
		}
		return list;
	}

	@Override
	public User getUserById(int id) {
		Optional<User> opt=repository.findById(id);
		if(!opt.isPresent()) {
			throw new UserException("User not found.");
		}
		return opt.get();
	}


}
