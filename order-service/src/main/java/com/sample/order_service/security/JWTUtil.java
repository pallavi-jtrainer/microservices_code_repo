package com.sample.order_service.security;

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
	
	public JWTUtil(@Value("${JWT_Secret:eyJhbGciOiJIUzI1NiJ9.ew0KICAic3ViIjogIjEyMzQ1Njc4OTAiLA0KICAibmFtZSI6ICJBbmlzaCBOYXRoIiwNCiAgImlhdCI6IDE1MTYyMzkwMjINCn0.Xe8Oy0Ksl84WAEs20K_Sn_GQboI8Bsg53yFTd3IkfuA}") String secret) {
		this.key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public Jws<Claims> validate(String token) {
		return Jwts.parserBuilder().setSigningKey(key)
				.build().parseClaimsJws(token);
	}
}
