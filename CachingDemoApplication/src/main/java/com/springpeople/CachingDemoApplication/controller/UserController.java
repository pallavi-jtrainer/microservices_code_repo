package com.springpeople.CachingDemoApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springpeople.CachingDemoApplication.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users/{userId}")
	public String getUserData(@PathVariable String userId) {
		return service.getUserDetails(userId);
	}
}
