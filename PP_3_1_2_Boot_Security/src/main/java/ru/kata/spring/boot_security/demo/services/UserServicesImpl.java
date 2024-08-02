package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.configs.WebSecurityConfig;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {
	private UserRepository userRepository;
	
	
	@Autowired
	public UserServicesImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities((Collection) user.getRoles()));
		
	}
	
	@Override
	public User findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}
	
	//
	@Override
	@Transactional
	public void add(User user) {
		User existingUser = new User();
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
		existingUser.setEmail(user.getEmail());
		existingUser.setAge(user.getAge());
		existingUser.setRoles(user.getRoles());
		userRepository.save(existingUser);
	}
	
	@Override
	@Transactional
	public void update(Long id, User user) {
		if (id == null) {
			throw new IllegalArgumentException("ID не может быть null");
		}
		User existingUser = userRepository.getById(id);
		existingUser.setUsername(user.getUsername());
		if (user.getPassword() != null) {
			existingUser.setPassword(WebSecurityConfig.passwordEncoder().encode(user.getPassword()));
		}
		
		existingUser.setEmail(user.getEmail());
		existingUser.setAge(user.getAge());
		if (user.getRoles() != null) {
			existingUser.setRoles(user.getRoles());
		}
		
		userRepository.save(existingUser);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public User edit(Long id) {
		return userRepository.findById(id).get();
	}
	
	
}
