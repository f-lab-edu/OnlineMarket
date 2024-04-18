package com.market.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import com.market.global.define.HeaderKey;
import com.market.global.util.TokenUtil;
import com.market.repository.mapper.RedisTemplateMapper;
import com.market.webInterface.exception.UnauthorizedException;
import com.market.webInterface.interceptor.AuthInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class AuthInterceptorTest {
	@InjectMocks
	private AuthInterceptor authInterceptor;
	@Mock
	private RedisTemplateMapper redisRepository;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	final String token = TokenUtil.createNewToken();

	@DisplayName("인증 살패_잘못된 헤더")
	@Test
	public void invaildHeaderAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("");
		// when
		final UnauthorizedException result = assertThrows(UnauthorizedException.class,
			() -> authInterceptor.preHandle(request, response, any(Object.class)));
		//then
		assertThat(result.getError().getCode()).isEqualTo("UNAUTHORIZED");
	}

	@DisplayName("인증 살패_유효하지 않은 토큰")
	@Test
	public void invaildTokenAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + "");
		when(redisRepository.get("")).thenReturn(null);
		// when
		final UnauthorizedException result = assertThrows(UnauthorizedException.class,
			() -> authInterceptor.preHandle(request, response, new Object()));
		//then
		assertThat(result.getError().getCode()).isEqualTo("UNAUTHORIZED");
	}

	@DisplayName("인증 성공")
	@Test
	public void successAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + token);
		when(redisRepository.get(token)).thenReturn(Optional.of("test@test.com"));
		// when
		assertThat(authInterceptor.preHandle(request, response, new Object())).isTrue();
	}
}
