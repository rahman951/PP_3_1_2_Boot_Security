package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;


public interface UserServices extends UserDetailsService {
	List<User> findAll();
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(User user);
	
	
}
