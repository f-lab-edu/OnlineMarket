package com.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.market.application.service.CreateUserService;
import com.market.application.service.LoginService;
import com.market.application.service.dto.LoginResponseDto;
import com.market.controller.dto.LoginResponse;
import com.market.controller.dto.SignInRequest;
import com.market.controller.dto.SignUpRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final CreateUserService createUserService;
	private final LoginService loginService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@Valid @RequestBody final SignUpRequest request) {
		createUserService.signUp(request.toDto());
	}

	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody final SignInRequest request) {
		LoginResponseDto dto = loginService.login(request.toDto());
		return new LoginResponse(dto.getToken());
	}
}
