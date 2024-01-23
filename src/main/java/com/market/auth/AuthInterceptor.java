package com.market.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.market.auth.exception.UnauthorizedException;
import com.market.auth.repository.RedisRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	private final RedisRepository redisRepository;

	public AuthInterceptor(@Qualifier("inMemoryRedisRepository") RedisRepository redisRepository) {
		this.redisRepository = redisRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		UnauthorizedException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (!token.isBlank() && token.startsWith("Bearer")) {
			token = token.replace("Bearer ", "");
			Optional.ofNullable(redisRepository.get(token)).orElseThrow(UnauthorizedException::new);
		} else {
			throw new UnauthorizedException();
		}
		return true;
	}
}
