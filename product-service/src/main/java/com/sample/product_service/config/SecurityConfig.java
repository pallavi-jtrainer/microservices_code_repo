package com.sample.product_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sample.product_service.security.JWTUtil;
import com.sample.product_service.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final JWTUtil jwtUtil;
	
	public SecurityConfig(JWTUtil jwtUtil) { this.jwtUtil = jwtUtil; }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/actuator/**").permitAll()
					.anyRequest().authenticated()
					);
		http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}
