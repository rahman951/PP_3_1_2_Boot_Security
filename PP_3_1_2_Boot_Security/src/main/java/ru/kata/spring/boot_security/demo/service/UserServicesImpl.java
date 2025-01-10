package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {
	
	private final UserRepository userRepository;
	
	
	public UserServicesImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public void addUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email уже занят!");
		}
		
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		
		// Шифруем пароль перед сохранением
		user.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
		
		// Сохраняем пользователя
		userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void updateUser(User user) {
		if (!userRepository.existsById(user.getId())) {
			throw new IllegalArgumentException("User с таким ID не существует");
		}
		
		// Обновляем пользователя (включая перезапись пароля, если он был изменен)
		if (user.getPassword() != null && !user.getPassword().isEmpty()) {
			user.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
		}
		
		userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void deleteUser(User user) {
		if (!userRepository.existsById(user.getId())) {
			throw new IllegalArgumentException("User с таким ID не существует");
		}
		
		userRepository.deleteById(user.getId());
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities("ROLE_" + user.getRole().name()) // Добавляем "ROLE_" для совместимости с Security
				.build();
	}
}