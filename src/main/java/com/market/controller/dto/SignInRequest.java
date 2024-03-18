package com.market.controller.dto;

import com.market.application.service.dto.SignInRequestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignInRequest {
	@NotBlank
	private String email;
	@NotBlank
	private String password;

	public SignInRequestDto toDto() {
		return new SignInRequestDto(this.email, this.password);
	}
}
