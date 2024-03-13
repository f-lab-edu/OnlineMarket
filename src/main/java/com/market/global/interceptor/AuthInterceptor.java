package com.market.global.interceptor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.market.global.define.HeaderKey;
import com.market.global.exception.UnauthorizedException;
import com.market.repository.interfaces.RedisRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	private final RedisRepository redisRepository;

	@Autowired
	public AuthInterceptor(@Qualifier("redisTemplateMapper") RedisRepository redisRepository) {
		this.redisRepository = redisRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		UnauthorizedException {
		String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
			.orElseThrow(UnauthorizedException::new);
		if (token.startsWith(HeaderKey.BEARER)) {
			token = token.replace(HeaderKey.BEARER, "");
			Optional.ofNullable(redisRepository.get(token)).orElseThrow(UnauthorizedException::new);
		} else {
			throw new UnauthorizedException();
		}
		return true;
	}
}
