package com.market.user;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.market.app.domain.App;
import com.market.app.repository.AppRepository;
import com.market.auth.repository.RedisTemplateRepository;
import com.market.global.dto.RedisTokenDto;
import com.market.user.controller.LoginResponse;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.domain.User;
import com.market.user.domain.UserDeviceApps;
import com.market.user.repository.UserDeviceAppsRepository;
import com.market.user.repository.UserRepository;
import com.market.user.service.TokenLoginService;

@ExtendWith(MockitoExtension.class)
public class TokenLoginServiceTest {
	@InjectMocks
	private TokenLoginService loginService;
	@Mock
	private RedisTemplateRepository redisRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private AppRepository appRepository;
	@Mock
	private UserDeviceAppsRepository userDeviceAppsRepository;
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

	@DisplayName("로그인 실패_존재하지 않는 앱")
	@Test
	public void notFoundAppSignIn() {
		// given
		User user = signInRequestDto().toEntity();
		when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(
			Optional.of(noneMembershipUser()));
		when(appRepository.findByName(any(String.class))).thenReturn(Optional.empty());
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> loginService.login(signInRequestDto()));
		// then
		assertThat(result.getMessage()).isEqualTo("존재하지 않는 앱입니다.");
	}

	@DisplayName("로그인 실패_멤버쉽이 필요한 서비스")
	@Test
	public void noneMembershipUserSignIn() {
		// given
		User user = signInRequestDto().toEntity();
		when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(
			Optional.of(noneMembershipUser()));
		when(appRepository.findByName(any(String.class))).thenReturn(Optional.of(app()));
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> loginService.login(signInRequestDto()));
		// then
		assertThat(result.getMessage()).isEqualTo("멤버쉽이 필요한 서비스입니다.");
	}

	@DisplayName("로그인 성공")
	@Test
	public void successSignIn() {
		// given
		SignInRequestDto dto = signInRequestDto();
		User user = membershipUser();
		when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(
			Optional.of(user));
		when(appRepository.findByName(any(String.class))).thenReturn(Optional.of(app()));
		// when
		LoginResponse response = loginService.login(dto);
		// then
		then(userDeviceAppsRepository).should(times(1))
			.insertUserDeviceApps(any(UserDeviceApps.class));
		then(redisRepository).should(times(1))
			.set(any(Long.class), any(RedisTokenDto.class));
		assertThat(response.getToken()).isNotNull();
	}

	@DisplayName("로그아웃 성공")
	@Test
	public void successLogout() {
		// given
		// when
		loginService.logout(1L);
		// then
		then(redisRepository).should(times(1))
			.delete(any(Long.class));
		then(userDeviceAppsRepository).should(times(1))
			.deleteUserDeviceAppsById(any(Long.class));
	}

	private SignInRequestDto signInRequestDto() {
		return SignInRequestDto.builder()
			.email(email)
			.password(password)
			.appName("Coupang")
			.device("iPhone13-aa")
			.build();
	}

	private User noneMembershipUser() {
		return User.builder()
			.id(1L)
			.email(email)
			.password(password)
			.name("김테스트")
			.tel("01011112222")
			.isMembership(false)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
	}

	private User membershipUser() {
		return User.builder()
			.id(1L)
			.email(email)
			.password(password)
			.name("김테스트")
			.tel("01011112222")
			.isMembership(true)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
	}

	private App app() {
		return App.builder()
			.id(1L)
			.name("Coupang")
			.isMembership(true)
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
	}
}
