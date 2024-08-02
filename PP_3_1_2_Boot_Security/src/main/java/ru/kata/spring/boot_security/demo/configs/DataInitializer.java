package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserServicesImpl;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataInitializer {
	@Bean
	public CommandLineRunner initData(UserServicesImpl userService, RoleRepository roleRepository) {
		return args -> {
			Role userRole = new Role();
			userRole.setName("ROLE_USER");
			roleRepository.save(userRole);
			
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
			
			List<Role> adminRoles = new ArrayList<>();
			adminRoles.add(userRole);
			adminRoles.add(adminRole);
			
			List<Role> userRoles = new ArrayList<>();
			userRoles.add(userRole);
			
			
//			User user = new User();
//			user.setUsername("user");
//			user.setAge((byte) 100);
//			user.setEmail("user@mail.ru");
//			user.setPassword("$2a$12$SPJ5XVqaBEWd2Xxk5b6kVeORzaSx9U5qNFkbHtUghC9zneEC2Uv9K");
//			user.setRoles(userRoles);
//			userService.add(user);
//
//
//			User admin = new User();
//			admin.setAge((byte) 50);
//			admin.setUsername("admin");
//			admin.setEmail("admin@mail.ru");
//			admin.setPassword("$2a$12$SPJ5XVqaBEWd2Xxk5b6kVeORzaSx9U5qNFkbHtUghC9zneEC2Uv9K"); // 123
//			admin.setRoles(adminRoles);
//			userService.add(admin);
		};
	}
}
