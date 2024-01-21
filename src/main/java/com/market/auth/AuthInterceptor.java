package com.market.auth;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.market.auth.exception.UnauthorizedException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	private HashMap<String, String> tokens = new HashMap<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		UnauthorizedException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!token.isBlank() && token.startsWith("Bearer")) {
			token = token.replace("Bearer", "");
			Optional.ofNullable(tokens.get(token)).orElseThrow(UnauthorizedException::new);
		} else {
			throw new UnauthorizedException();
		}
		return true;
	}
}
