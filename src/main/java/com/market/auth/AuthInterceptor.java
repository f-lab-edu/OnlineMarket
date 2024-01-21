package com.market.auth;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	private HashMap<String, String> tokens = new HashMap<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		UnauthorizedException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		try {
			if (!token.isBlank() && token.startsWith("Bearer")) {
				token = token.replace("Bearer", "");
				tokens.get(token);
			}
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		return true;
	}
}
