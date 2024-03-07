package com.market.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.market.auth.exception.UnauthorizedException;
import com.market.auth.repository.RedisRepository;
import com.market.global.define.HeaderKey;
import com.market.global.dto.RedisTokenDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
	private final RedisRepository redisRepository;

	@Autowired
	public AuthInterceptor(@Qualifier("redisTemplateRepository") RedisRepository redisRepository) {
		this.redisRepository = redisRepository;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
			.orElseThrow(UnauthorizedException::new);
		Long userDeviceAppId = Long.parseLong(Optional.ofNullable(request.getHeader(HeaderKey.USER_DEVICE_APPS_ID))
			.orElseThrow(UnauthorizedException::new));
		if (token.startsWith(HeaderKey.BEARER)) {
			token = token.replace(HeaderKey.BEARER, "");
			Optional<RedisTokenDto> redisTokenDto = Optional.ofNullable(redisRepository.get(userDeviceAppId))
				.orElseThrow(UnauthorizedException::new);
			if (!token.equals(redisTokenDto.get().getToken())) {
				throw new UnauthorizedException();
			}
		} else {
			throw new UnauthorizedException();
		}
		return true;
	}
}
