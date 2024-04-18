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
import com.market.application.exception.DuplicatedUserEmailException;
import com.market.application.exception.errorCode.ApplicationErrorCode;
import com.market.application.usecase.CreateUserUseCase;
import com.market.application.usecase.LoginUseCase;
import com.market.application.usecase.dto.LoginResponseDto;
import com.market.application.usecase.dto.SignInRequestDto;
import com.market.application.usecase.dto.SignUpRequestDto;
import com.market.global.handler.GlobalExceptionHandler;
import com.market.webInterface.controller.UserController;
import com.market.webInterface.controller.dto.SignInRequest;
import com.market.webInterface.controller.dto.SignUpRequest;
import com.market.webInterface.exception.errorCode.WebInterfaceErrorCode;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@InjectMocks
	private UserController userController;
	@Mock
	private CreateUserUseCase createUserUseCase;
	@Mock
	private LoginUseCase loginService;
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setControllerAdvice(new GlobalExceptionHandler())
			.build();
	}

	@DisplayName("회원가입 실패_잘못된 파라미터")
	@ParameterizedTest
	@MethodSource("invaildSignUpRequest")
	public void invalidDtoSignUp(String email, String name, String password, String tel) throws Exception {
		// given
		final String url = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(SignUpRequest.builder()
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
			.andExpect(jsonPath("$.code").value(WebInterfaceErrorCode.INVALID_PARAMETER.getCode()));
	}

	@DisplayName("회원가입 실패_이미 등록된 회원")
	@Test
	public void duplicatedUserSignUp() throws Exception {
		// given
		final String url = "/users";
		doThrow(new DuplicatedUserEmailException(ApplicationErrorCode.DUPLICATED_USER_EMAIL))
			.when(createUserUseCase).signUp(any(SignUpRequestDto.class));
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(signUpRequest()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code").value(ApplicationErrorCode.DUPLICATED_USER_EMAIL.getCode()));
	}

	@DisplayName("회원가입 성공")
	@Test
	public void successSignUp() throws Exception {
		// given
		final String url = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(signUpRequest()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isCreated());
	}

	@DisplayName("로그인 실패_잘못된 파라미터")
	@ParameterizedTest
	@MethodSource("invaildSignInRequest")
	public void invalidDtoLogin(String email, String password) throws Exception {
		// given
		final String url = "/users/login";
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(SignInRequest.builder()
					.email(email)
					.password(password)
					.build()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isBadRequest())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code").value(WebInterfaceErrorCode.INVALID_PARAMETER.getCode()));
	}

	// @DisplayName("로그인 실패_존재하지 않는 회원")
	// @Test
	// public void notFoundUserLogin() throws Exception {
	// 	// given
	// 	final String url = "/users/login";
	// 	doThrow(new IllegalArgumentException("존재하지 않는 회원입니다."))
	// 		.when(loginService).login(any(SignInRequestDto.class));
	// 	// when
	// 	final ResultActions resultActions = mockMvc.perform(
	// 		MockMvcRequestBuilders.post(url)
	// 			.content(objectMapper.writeValueAsString(signInRequest()))
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 	);
	// 	// then
	// 	resultActions.andExpect(status().isInternalServerError())
	// 		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
	// 		.andExpect(jsonPath("$.code").value(ControllerErrorCode.INTERNAL_SERER_ERROR.name()));
	// }

	@DisplayName("로그인 성공")
	@Test
	public void successLogin() throws Exception {
		// given
		final String url = "/users/login";
		final SignInRequest request = signInRequest();
		when(loginService.login(any(SignInRequestDto.class))).thenReturn(new LoginResponseDto("df"));
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isOk());
	}

	private SignUpRequest signUpRequest() {
		return SignUpRequest.builder()
			.email("test@test.com")
			.name("테스트")
			.password("testtest12!")
			.tel("01012341234")
			.build();
	}

	private SignInRequest signInRequest() {
		return SignInRequest.builder()
			.email("test@test.com")
			.password("testtest12!")
			.build();
	}

	private static Stream<Arguments> invaildSignUpRequest() {
		return Stream.of(
			Arguments.of("", "테스트", "test", "01012341234"),
			Arguments.of("test@test.com", "", "test", "01012341234"),
			Arguments.of("test@test.com", "테스트", "", "01012341234"),
			Arguments.of("test@test.com", "테스트", "test", ""),
			Arguments.of("test", "테스트", "test", ""),
			Arguments.of("test@test.com", "테스트", "test", "0101234"),
			Arguments.of(null, "테스트", "test", "0101234"),
			Arguments.of("test@test.com", "테스트", "test", "01012341234"),
			Arguments.of("test@test.com", "테스트", "testtest12", "01012341234"),
			Arguments.of("test@test.com", "테스트", "testtest!!", "01012341234")
		);
	}

	private static Stream<Arguments> invaildSignInRequest() {
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
