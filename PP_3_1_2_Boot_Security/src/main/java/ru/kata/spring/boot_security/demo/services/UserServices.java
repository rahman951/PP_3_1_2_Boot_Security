package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface UserServices extends UserDetailsService {
	
	
	Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
	
	User findUserByUsername(String username);
	
	@Override
	@Transactional
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	
	User findByUsername(String userName);
	
	void add(User user);
	
	
	void update(Long id, User user);
	
	void delete(Long id);
	
	List<User> findAll();
	
	User edit(Long id);
}
