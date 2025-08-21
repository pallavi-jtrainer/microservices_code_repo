package com.sample.auth_service.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final Key key;
	private final long expiryMs = 3600000L;
	
	public JwtUtil(@Value("${JWT_SECRET:eyJhbGciOiJIUzI1NiJ9.ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoIiwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0.Xe8Oy0Ksl84WAEs20K_Sn_GQboI8Bsg53yFTd3IkfuA}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(String username, String roles) {
		return Jwts.builder()
				.setSubject(username)
				.claim("roles", roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiryMs))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
}
