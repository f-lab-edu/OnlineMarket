package com.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.market.application.dto.LoginResponseDto;
import com.market.application.dto.SignInRequestDto;
import com.market.application.dto.SignUpRequestDto;
import com.market.application.service.CreateUserService;
import com.market.application.service.LoginService;

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
	public void signUp(@Valid @RequestBody final SignUpRequestDto dto) {
		createUserService.signUp(dto);
	}

	@PostMapping("/login")
	public LoginResponseDto login(@Valid @RequestBody final SignInRequestDto dto) {
		return loginService.login(dto);
	}
}
