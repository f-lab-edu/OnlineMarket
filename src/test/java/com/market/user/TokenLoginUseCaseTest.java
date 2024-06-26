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

import com.market.application.domain.dto.User;
import com.market.application.usecase.TokenLoginUseCase;
import com.market.application.usecase.dto.LoginResponseDto;
import com.market.application.usecase.dto.SignInRequestDto;
import com.market.global.util.TokenUtil;
import com.market.repository.implementation.UserRepositoryImpl;
import com.market.repository.mapper.RedisTemplateMapper;

@ExtendWith(MockitoExtension.class)
public class TokenLoginUseCaseTest {
	@InjectMocks
	private TokenLoginUseCase loginService;
	@Mock
	private RedisTemplateMapper redisRepository;
	@Mock
	private UserRepositoryImpl userRepository;
	@Mock
	private TokenUtil tokenUtil;
	private final String email = "tset@test.com";
	private final String password = "test";

	@DisplayName("로그인 실패_로그인 정보가 올바르지 않음")
	@Test
	public void notFoundUserSignIn() {
		// given
		User user = signInRequestDto().toDomain();
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
		User user = dto.toDomain();
		when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.of(user));
		// when
		LoginResponseDto response = loginService.login(dto);
		// then
		assertThat(response.getToken()).isNotNull();
	}

	private SignInRequestDto signInRequestDto() {
		return new SignInRequestDto(email, password);
	}
}
