package com.market.application.usecase.dto;

import com.market.application.domain.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequestDto {
	private String name;
	private String email;
	private String password;
	private String tel;

	public User toDomain() {
		return User.builder()
			.name(this.name)
			.email(this.email)
			.password(this.password)
			.tel(this.tel)
			.build();
	}
}
