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

import com.market.auth.repository.InMemoryRedisRepository;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.domain.User;
import com.market.user.repository.UserRepository;
import com.market.user.service.TokenLoginService;

@ExtendWith(MockitoExtension.class)
public class TokenLoginServiceTest {
	@InjectMocks
	private TokenLoginService loginService;
	@Mock
	private InMemoryRedisRepository redisRepository;
	@Mock
	private UserRepository userRepository;
	private final String email = "tset@test.com";
	private final String password = "test";

	@DisplayName("로그인 실패_회원이 존재하지 않음")
	@Test
	public void notFoundUserSignIn() {
		// given
		User user = signInRequestDto().toEntity();
		given(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).willReturn(Optional.empty());
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> loginService.login(signInRequestDto()));
		// then
		assertThat(result.getMessage()).isEqualTo("존재하지 않는 회원입니다.");
	}

	@DisplayName("로그인 성공")
	@Test
	public void successSignIn() {
		// given
		SignInRequestDto dto = signInRequestDto();
		User user = dto.toEntity();
		given(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).willReturn(Optional.of(user));
		// when
		loginService.login(dto);
		// then
		then(redisRepository).should(times(1)).set(any(String.class), any(String.class));
	}

	private SignInRequestDto signInRequestDto() {
		return SignInRequestDto.builder()
			.email(email)
			.password(password)
			.build();
	}
}
