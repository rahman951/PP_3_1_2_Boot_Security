package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserServicesImpl;


import java.util.ArrayList;
import java.util.List;

import static ru.kata.spring.boot_security.demo.entities.Role.ADMIN;

@Configuration
public class DataInitializer {
	@Bean
	public CommandLineRunner initData(UserServicesImpl userService) {
		return args -> {
			
			
			User user = new User();
			
			user.setEmail("user@mail.ru");
			user.setPassword("123");
			user.setRole(ADMIN);
			userService.addUser(user);
			
		};
	}
}
