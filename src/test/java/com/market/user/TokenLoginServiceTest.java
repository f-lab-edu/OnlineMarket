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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.domain.User;
import com.market.user.repository.UserRepository;
import com.market.user.service.TokenLoginService;
import com.market.util.TokenUtil;

@ExtendWith(MockitoExtension.class)
public class TokenLoginServiceTest {
	@InjectMocks
	private TokenLoginService loginService;
	@Mock
	private RedisTemplate<String, String> redisTemplate;
	@Mock
	private ValueOperations valueOperations;
	@Mock
	private UserRepository userRepository;
	private final String email = "tset@test.com";
	private final String password = "test";
	private final String token = TokenUtil.createNewToken();

	@DisplayName("로그인 실패_로그인 정보가 올바르지 않음")
	@Test
	public void notFoundUserSignIn() {
		// given
		User user = signInRequestDto().toEntity();
		given(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).willReturn(Optional.empty());
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
		given(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).willReturn(Optional.of(user));
		given(redisTemplate.opsForValue()).willReturn(valueOperations);
		// when
		loginService.login(dto);
	}

	private SignInRequestDto signInRequestDto() {
		return SignInRequestDto.builder()
			.email(email)
			.password(password)
			.build();
	}
}
