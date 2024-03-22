package com.market.webInterface.interceptor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.market.application.repository.interfaces.RedisRepository;
import com.market.global.define.HeaderKey;
import com.market.webInterface.exception.EmptyAuthorizationHeaderException;
import com.market.webInterface.exception.EmptyTokenException;
import com.market.webInterface.exception.InvalidHeaderKeyException;
import com.market.webInterface.exception.UnauthorizedException;
import com.market.webInterface.exception.errorCode.WebInterfaceErrorCode;

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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			String token = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
				.orElseThrow(
					() -> new EmptyAuthorizationHeaderException(WebInterfaceErrorCode.EMPTY_AUTHORIZATION_HEADER));
			if (token.startsWith(HeaderKey.BEARER)) {
				token = token.replace(HeaderKey.BEARER, "");
				Optional.ofNullable(redisRepository.get(token))
					.orElseThrow(() -> new EmptyTokenException(WebInterfaceErrorCode.EMPTY_TOKEN));
			} else {
				throw new InvalidHeaderKeyException(WebInterfaceErrorCode.INVALID_HEADER_KEY);
			}
		} catch (EmptyAuthorizationHeaderException | EmptyTokenException | InvalidHeaderKeyException e) {
			throw new UnauthorizedException(WebInterfaceErrorCode.UNAUTHORIZED, e.getError().getMessage());
		}
		return true;
	}
}
