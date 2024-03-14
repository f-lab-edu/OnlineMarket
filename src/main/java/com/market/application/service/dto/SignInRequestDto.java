package com.market.application.service.dto;

import com.market.application.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequestDto {
	private String email;
	private String password;

	public User toDomain() {
		return User.builder()
			.email(this.email)
			.password(this.password)
			.build();
	}
}
