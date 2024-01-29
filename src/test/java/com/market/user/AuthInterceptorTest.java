package com.market.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.auth.define.HeaderKey;
import com.market.error.ErrorCode;
import com.market.user.controller.LoginResponse;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.controller.dto.SignUpRequestDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthInterceptorTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	protected WebApplicationContext ctx;

	@BeforeEach
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@DisplayName("인증 살패_잘못된 헤더")
	@Test
	public void invaildHeaderAuthorization() throws Exception {
		// <editor-fold desc="회원가입">
		// given
		final String signUpUrl = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(signUpUrl)
			.content(objectMapper.writeValueAsString(SignUpRequestDto.builder()
				.email("test1@test.com")
				.name("테스트")
				.password("test")
				.tel("01012341234")
				.build()))
			.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isCreated());
		// </editor-fold>
		// <editor-fold desc="로그인">
		// given
		final String signInUrl = "/users/login";
		// when
		final ResultActions resultActions2 = mockMvc.perform(
			MockMvcRequestBuilders.post(signInUrl)
				.content(objectMapper.writeValueAsString(SignInRequestDto.builder()
					.email("test1@test.com")
					.password("test")
					.build()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		final String response = resultActions2.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		// </editor-fold>
		// <editor-fold desc="인증">
		// given
		final String token = objectMapper.readValue(response, LoginResponse.class).getToken();
		final String authTestUrl = "/users/auth-test";

		// when
		final ResultActions resultActions3 = mockMvc.perform(
			MockMvcRequestBuilders.get(authTestUrl)
				.header(HttpHeaders.AUTHORIZATION, token)
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions3.andExpect(status().isUnauthorized())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code").value(ErrorCode.UNAUTHORIZED.name()))
			.andExpect(jsonPath("$.message").value("인증되지 않은 사용자입니다."));
		// </editor-fold>
	}

	@DisplayName("인증 살패_유효하지 않은 토큰")
	@Test
	public void invaildTokenAuthorization() throws Exception {
		// <editor-fold desc="회원가입">
		// given
		final String signUpUrl = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(signUpUrl)
			.content(objectMapper.writeValueAsString(SignUpRequestDto.builder()
				.email("test2@test.com")
				.name("테스트")
				.password("test")
				.tel("01012341234")
				.build()))
			.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isCreated());
		// </editor-fold>
		// <editor-fold desc="로그인">
		// given
		final String signInUrl = "/users/login";
		// when
		final ResultActions resultActions2 = mockMvc.perform(
			MockMvcRequestBuilders.post(signInUrl)
				.content(objectMapper.writeValueAsString(SignInRequestDto.builder()
					.email("test2@test.com")
					.password("test")
					.build()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions2.andExpect(status().isOk());
		// </editor-fold>
		// <editor-fold desc="인증">
		// given
		final String authTestUrl = "/users/auth-test";

		// when
		final ResultActions resultActions3 = mockMvc.perform(
			MockMvcRequestBuilders.get(authTestUrl)
				.header(HttpHeaders.AUTHORIZATION, "")
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions3.andExpect(status().isUnauthorized())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.code").value(ErrorCode.UNAUTHORIZED.name()))
			.andExpect(jsonPath("$.message").value("인증되지 않은 사용자입니다."));
		// </editor-fold>
	}

	@DisplayName("인증 성공")
	@Test
	public void successAuthorization() throws Exception {
		// <editor-fold desc="회원가입">
		// given
		final String signUpUrl = "/users";
		// when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(signUpUrl)
			.content(objectMapper.writeValueAsString(SignUpRequestDto.builder()
				.email("test3@test.com")
				.name("테스트")
				.password("test")
				.tel("01012341234")
				.build()))
			.contentType(MediaType.APPLICATION_JSON));
		// then
		resultActions.andExpect(status().isCreated());
		// </editor-fold>
		// <editor-fold desc="로그인">
		// given
		final String signInUrl = "/users/login";
		// when
		final ResultActions resultActions2 = mockMvc.perform(
			MockMvcRequestBuilders.post(signInUrl)
				.content(objectMapper.writeValueAsString(SignInRequestDto.builder()
					.email("test3@test.com")
					.password("test")
					.build()))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		final String response = resultActions2.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();
		// </editor-fold>
		// <editor-fold desc="인증">
		// given
		final String token = objectMapper.readValue(response, LoginResponse.class).getToken();
		final String authTestUrl = "/users/auth-test";

		// when
		final ResultActions resultActions3 = mockMvc.perform(
			MockMvcRequestBuilders.get(authTestUrl)
				.header(HttpHeaders.AUTHORIZATION, HeaderKey.BEARER + token)
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions3.andExpect(status().isOk());
		// </editor-fold>
	}
}
