package com.myapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.myapp.entity.User;

public interface UserService extends UserDetailsService{
	
	public User findByUsername(String username);
	
	public void save(User user);
}
