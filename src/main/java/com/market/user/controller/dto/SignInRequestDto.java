package com.market.user.controller.dto;

import com.market.user.domain.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInRequestDto {
	@NotBlank
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
