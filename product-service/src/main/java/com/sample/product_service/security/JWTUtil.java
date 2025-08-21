package com.sample.product_service.security;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	private final Key key;
	
	public JWTUtil(@Value("${JWT_Secret:my_secret_key}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public Jws<Claims> validate(String token) {
		return Jwts.parserBuilder().setSigningKey(key)
				.build().parseClaimsJws(token);
	}
}
