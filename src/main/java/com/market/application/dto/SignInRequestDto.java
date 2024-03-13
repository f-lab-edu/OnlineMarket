package com.market.application.dto;

import com.market.application.domain.User;

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

	public User toDomain() {
		return User.builder()
			.email(this.email)
			.password(this.password)
			.build();
	}
}
