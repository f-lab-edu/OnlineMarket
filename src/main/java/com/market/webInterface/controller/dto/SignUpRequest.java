package com.market.webInterface.controller.dto;

import com.market.application.usecase.dto.SignUpRequestDto;
import com.market.webInterface.controller.dto.annotation.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequest {
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

	public SignUpRequestDto toDto() {
		return new SignUpRequestDto(this.name, this.email, this.password, this.tel);
	}
}
