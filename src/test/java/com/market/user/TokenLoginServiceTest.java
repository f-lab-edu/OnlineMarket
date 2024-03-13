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

import com.market.application.domain.User;
import com.market.application.dto.LoginResponseDto;
import com.market.application.dto.SignInRequestDto;
import com.market.application.service.TokenLoginService;
import com.market.global.util.TokenUtil;
import com.market.repository.mapper.RedisTemplateMapper;
import com.market.repository.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
public class TokenLoginServiceTest {
	@InjectMocks
	private TokenLoginService loginService;
	@Mock
	private RedisTemplateMapper redisRepository;
	@Mock
	private UserMapper userRepository;
	@Mock
	private TokenUtil tokenUtil;
	private final String email = "tset@test.com";
	private final String password = "test";

	@DisplayName("로그인 실패_로그인 정보가 올바르지 않음")
	@Test
	public void notFoundUserSignIn() {
		// given
		User user = signInRequestDto().toEntity();
		when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.empty());
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> loginService.login(signInRequestDto()));
		// then
		assertThat(result.getMessage()).isEqualTo("로그인 정보가 올바르지 않습니다.");
	}

	@DisplayName("로그인 성공")
	@Test
	public void successSignIn() {
		// given
		SignInRequestDto dto = signInRequestDto();
		User user = dto.toEntity();
		when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.of(user));
		// when
		LoginResponseDto response = loginService.login(dto);
		// then
		assertThat(response.getToken()).isNotNull();
	}

	private SignInRequestDto signInRequestDto() {
		return SignInRequestDto.builder()
			.email(email)
			.password(password)
			.build();
	}
}
