package com.example.demo.service;

import com.example.demo.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	
}
