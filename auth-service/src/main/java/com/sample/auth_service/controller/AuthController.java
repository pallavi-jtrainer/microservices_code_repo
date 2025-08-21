package com.sample.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.auth_service.dto.AuthRequest;
import com.sample.auth_service.dto.AuthResponse;
import com.sample.auth_service.entity.User;
import com.sample.auth_service.security.JwtUtil;
import com.sample.auth_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody AuthRequest req) {
		User u = authService.register(req.getUsername(), req.getPassword(), "ROLE_USER");
		return ResponseEntity.ok(u);	
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(AuthRequest req) {
		User u = authService.getUser(req.getUsername());
		
		if(u == null || !passwordEncoder.matches(req.getPassword(), u.getPassword())) {
			return ResponseEntity.status(401).body("Invalid Credentials");
		}
		
		String token = jwtUtil.generateToken(u.getUsername(), u.getPassword());
		
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
