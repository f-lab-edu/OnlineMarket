package com.market.user.controller.dto;

import com.market.user.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequestDto {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String password;

	public User toEntity() {
		return User.builder()
			.email(this.email)
			.password(this.password)
			.build();
	}
}
