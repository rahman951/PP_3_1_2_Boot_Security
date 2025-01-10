package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.service.UserServicesImpl;

@Controller
public class UserController {
	private UserServicesImpl userService;
	
	public UserController(UserServicesImpl userService) {
		this.userService = userService;
	}
	
	
	
	
	
}
