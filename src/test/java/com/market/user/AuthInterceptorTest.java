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

import com.market.auth.AuthInterceptor;
import com.market.auth.exception.UnauthorizedException;
import com.market.auth.repository.RedisTemplateRepository;
import com.market.global.define.HeaderKey;
import com.market.global.dto.RedisTokenDto;
import com.market.util.TokenUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class AuthInterceptorTest {
	@InjectMocks
	private AuthInterceptor authInterceptor;
	@Mock
	private RedisTemplateRepository redisRepository;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	private final String token = TokenUtil.createNewToken();
	private final String userDeviceAppsId = "1";

	@DisplayName("인증 살패_잘못된 Authorization 헤더")
	@Test
	public void invaildAuthorizationHeaderAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("");
		// when
		final RuntimeException result = assertThrows(UnauthorizedException.class,
			() -> authInterceptor.preHandle(request, response, any(Object.class)));
		//then
		assertThat(result.getMessage()).isEqualTo("인증되지 않은 사용자입니다.");
	}

	@DisplayName("인증 살패_잘못된 User-Device-App-Id 헤더")
	@Test
	public void invaildUserDeviceAppIdHeaderAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + token);
		when(request.getHeader(HeaderKey.USER_DEVICE_APPS_ID)).thenReturn(null);
		// when
		final RuntimeException result = assertThrows(UnauthorizedException.class,
			() -> authInterceptor.preHandle(request, response, any(Object.class)));
		//then
		assertThat(result.getMessage()).isEqualTo("인증되지 않은 사용자입니다.");
	}

	@DisplayName("인증 살패_유효하지 않은 기기")
	@Test
	public void invaildUserDeviceAppIdAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + token);
		when(request.getHeader(HeaderKey.USER_DEVICE_APPS_ID)).thenReturn(userDeviceAppsId);
		when(redisRepository.get(Long.parseLong(userDeviceAppsId))).thenReturn(null);
		// when
		final RuntimeException result = assertThrows(UnauthorizedException.class,
			() -> authInterceptor.preHandle(request, response, new Object()));
		//then
		assertThat(result.getMessage()).isEqualTo("인증되지 않은 사용자입니다.");
	}

	@DisplayName("인증 살패_유효하지 않은 토큰")
	@Test
	public void invaildTokenAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + "");
		when(request.getHeader(HeaderKey.USER_DEVICE_APPS_ID)).thenReturn(userDeviceAppsId);
		when(redisRepository.get(Long.parseLong(userDeviceAppsId)))
			.thenReturn(Optional.of(new RedisTokenDto(token, 0L)));
		// when
		final RuntimeException result = assertThrows(UnauthorizedException.class,
			() -> authInterceptor.preHandle(request, response, new Object()));
		//then
		assertThat(result.getMessage()).isEqualTo("인증되지 않은 사용자입니다.");
	}

	@DisplayName("인증 성공")
	@Test
	public void successAuthorization() throws Exception {
		// given
		when(request.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(HeaderKey.BEARER + token);
		when(request.getHeader(HeaderKey.USER_DEVICE_APPS_ID)).thenReturn(userDeviceAppsId);
		when(redisRepository.get(Long.parseLong(userDeviceAppsId)))
			.thenReturn(Optional.of(new RedisTokenDto(token, 0L)));
		// when
		assertThat(authInterceptor.preHandle(request, response, new Object())).isTrue();
	}
}
