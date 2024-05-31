package com.market.user.adapter.in.web.dto;

import com.market.user.adapter.in.web.dto.annotation.Password;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
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
}

