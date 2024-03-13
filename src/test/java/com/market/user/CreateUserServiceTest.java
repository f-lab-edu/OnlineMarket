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
import com.market.application.dto.SignUpRequestDto;
import com.market.application.service.CreateUserService;
import com.market.repository.implementation.UserRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {
	@InjectMocks
	private CreateUserService createUserService;
	@Mock
	private UserRepositoryImpl userRepository;
	private final String email = "test@test.com";

	@DisplayName("회원가입 실패_이미 회원 존재")
	@Test
	public void isDuplicatedUserSignUp() {
		// given
		SignUpRequestDto dto = signUpRequestDto();
		User user = dto.toDomain();
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> createUserService.signUp(dto));
		// then
		assertThat(result.getMessage()).isEqualTo("이미 등록된 회원입니다");
	}

	@DisplayName("회원가입 성공")
	@Test
	public void successUserSignUp() {
		// given
		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
		// when
		createUserService.signUp(signUpRequestDto());
		// then
		then(userRepository).should(times(1)).insertUser(any(User.class));
	}

	private SignUpRequestDto signUpRequestDto() {
		return SignUpRequestDto.builder()
			.email(email)
			.name("테스트")
			.password("test")
			.tel("01012341234")
			.build();
	}
}
