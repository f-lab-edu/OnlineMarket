package com.market.application.dto;

import com.market.global.annotation.Password;
import com.market.application.domain.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequestDto {
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@Password
	private String password;
	@NotBlank
	@Pattern(regexp = "[0-9]{11}")
	private String tel;

	public User toEntity() {
		return User.builder()
			.name(this.name)
			.email(this.email)
			.password(this.password)
			.tel(this.tel)
			.build();
	}
}
