package com.springpeople.CachingDemoApplication.service;

import org.springframework.cache.annotation.Cacheable;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Cacheable("users")
	public String getUserDetails(String userId) {
		String url = "https://jsonplaceholder.typicode.com/users/" + userId;
		
		return restTemplate.getForObject(url, String.class);
	}
}
