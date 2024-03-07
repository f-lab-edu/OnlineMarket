package com.market.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.market.global.define.HeaderKey;
import com.market.user.controller.dto.SignInRequestDto;
import com.market.user.controller.dto.SignUpRequestDto;
import com.market.user.service.CreateUserService;
import com.market.user.service.LoginService;

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
	public LoginResponse login(@Valid @RequestBody final SignInRequestDto dto) {
		return loginService.login(dto);
	}

	@GetMapping("/logout")
	public void login(@RequestHeader(HeaderKey.USER_DEVICE_APPS_ID) Long userDeviceAppsId) {
		loginService.logout(userDeviceAppsId);
	}
}
