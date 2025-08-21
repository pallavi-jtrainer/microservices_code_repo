package com.sample.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.auth_service.entity.User;
import com.sample.auth_service.exception.ResourceNotfoundException;
import com.sample.auth_service.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User register(String username, String password, String roles) {
		User u = new User();
		u.setUsername(username);
		u.setPassword(passwordEncoder.encode(password));
		u.setRoles(roles);
		return repo.save(u);
	}
	
	public User getUser(String username) {
		return repo.findByUsername(username).orElseThrow(
				() -> new ResourceNotfoundException("User with username: " + username + " not found"));
	}
}
