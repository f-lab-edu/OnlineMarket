package com.market.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.market.user.controller.dto.SignUpRequestDto;
import com.market.user.service.CreateUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final CreateUserService createUserService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@Valid @RequestBody final SignUpRequestDto dto) {
		createUserService.signUp(dto);
	}
}
