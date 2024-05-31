package com.market.user.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.market.user.adapter.in.web.dto.SignUpRequestDto;
import com.market.user.application.port.in.command.SignUpCommand;
import com.market.user.application.port.in.usecase.CreateUserUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final CreateUserUseCase createUserUseCase;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void signUp(@Valid @RequestBody final SignUpRequestDto request) {
		SignUpCommand command = new SignUpCommand(request.getName(), request.getEmail(), request.getPassword(),
			request.getTel());
		createUserUseCase.signUp(command);
	}
}
