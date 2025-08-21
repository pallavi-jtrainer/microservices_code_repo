package com.sample.product_service.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JWTUtil jwtUtil;
	
	public JwtAuthenticationFilter(JWTUtil jwtUtil) { this.jwtUtil = jwtUtil; }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		
		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			
			try {
				Claims claims = jwtUtil.validate(token).getBody();
				
				String username = claims.getSubject();
				
				var auth = new UsernamePasswordAuthenticationToken(username, null,
						List.of(new SimpleGrantedAuthority("ROLE_USER")));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception ex) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
