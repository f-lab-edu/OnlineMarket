package com.market.webInterface.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.market.application.usecase.CreateUserUseCase;
import com.market.application.usecase.LoginUseCase;
import com.market.application.usecase.dto.LoginResponseDto;
import com.market.webInterface.controller.dto.LoginResponse;
import com.market.webInterface.controller.dto.SignInRequest;
import com.market.webInterface.controller.dto.SignUpRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final CreateUserUseCase createUserUseCase;
	private final LoginUseCase loginService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@Valid @RequestBody final SignUpRequest request) {
		createUserUseCase.signUp(request.toDto());
	}

	@PostMapping("/login")
	public LoginResponse login(@Valid @RequestBody final SignInRequest request) {
		LoginResponseDto dto = loginService.login(request.toDto());
		return new LoginResponse(dto.getToken());
	}
}
