package com.market.user;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.error.ErrorCode;
import com.market.error.ErrorController;
import com.market.user.controller.UserController;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.controller.dto.SignUpRequestDto;
import com.market.user.service.CreateUserService;
import com.market.user.service.LoginService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@InjectMocks
	private UserController userController;
	@Mock
	private CreateUserService createUserService;
	@Mock
	private LoginService loginService;
  private ObjectMapper objectMapper;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setControllerAdvice(new ErrorController())
			.build();
	}

	@DisplayName("회원가입 실패_잘못된 파라미터")
	@ParameterizedTest
	@MethodSource("invaildSignUpRequestDto")
	public void invalidDtoSignUp(String email, String name, String password, String tel) throws Exception {
		// given
		final String url = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(SignUpRequestDto.builder()
					.name(name)
					.email(email)
					.password(password)
					.tel(tel)
					.build()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code").value(ErrorCode.BAD_REQUEST.name()));
	}

	@DisplayName("회원가입 실패_이미 등록된 회원")
	@Test
	public void duplicatedUserSignUp() throws Exception {
		// given
		final String url = "/users";
		doThrow(new IllegalArgumentException("이미 등록된 회원입니다"))
			.when(createUserService).signUp(any(SignUpRequestDto.class));
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(signUpRequestDto()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isInternalServerError())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code").value(ErrorCode.INTERNAL_SERER_ERROR.name()));
	}

	@DisplayName("회원가입 성공")
	@Test
	public void successSignUp() throws Exception {
		// given
		final String url = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(signUpRequestDto()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isCreated());
	}

	@DisplayName("로그인 실패_잘못된 파라미터")
	@ParameterizedTest
	@MethodSource("invaildSignInRequestDto")
	public void invalidDtoLogin(String email, String password) throws Exception {
		// given
		final String url = "/users/login";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(SignInRequestDto.builder()
					.email(email)
					.password(password)
					.build()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isBadRequest());
	}

	@DisplayName("로그인 성공")
	@Test
	public void successLogin() throws Exception {
		// given
		final String url = "/users/login";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(signInRequestDto()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isOk());
	}

	private SignUpRequestDto signUpRequestDto() {
		return SignUpRequestDto.builder()
			.email("test@test.com")
			.name("테스트")
			.password("test")
			.tel("01012341234")
			.build();
	}

	private SignInRequestDto signInRequestDto() {
		return SignInRequestDto.builder()
			.email("test@test.com")
			.password("test")
			.build();
	}

	private static Stream<Arguments> invaildSignUpRequestDto() {
		return Stream.of(
			Arguments.of("", "테스트", "test", "01012341234"),
			Arguments.of("test@test.com", "", "test", "01012341234"),
			Arguments.of("test@test.com", "테스트", "", "01012341234"),
			Arguments.of("test@test.com", "테스트", "test", ""),
			Arguments.of("test", "테스트", "test", ""),
			Arguments.of("test@test.com", "테스트", "test", "0101234"),
			Arguments.of(null, "테스트", "test", "0101234")
		);
	}

	private static Stream<Arguments> invaildSignInRequestDto() {
		return Stream.of(
			Arguments.of(null, null),
			Arguments.of("test@test.com", null),
			Arguments.of(null, "test"),
			Arguments.of("", "test"),
			Arguments.of("test@test.com", ""),
			Arguments.of("", "")
		);
	}
}
