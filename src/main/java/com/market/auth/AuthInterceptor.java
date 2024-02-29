package com.market.auth;

import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.market.auth.exception.UnauthorizedException;
import com.market.global.define.HeaderKey;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		UnauthorizedException {
		String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
			.orElseThrow(UnauthorizedException::new);
		if (token.startsWith(HeaderKey.BEARER)) {
			token = token.replace(HeaderKey.BEARER, "");
			Optional.ofNullable(redisTemplate.opsForValue().get(token)).orElseThrow(UnauthorizedException::new);
		} else {
			throw new UnauthorizedException();
		}
		return true;
	}
}
