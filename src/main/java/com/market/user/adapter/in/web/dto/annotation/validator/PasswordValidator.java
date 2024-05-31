package com.market.user.adapter.in.web.dto.annotation.validator;

import java.util.regex.Pattern;

import com.market.user.adapter.in.web.dto.annotation.Password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		return pattern.matcher(value).matches();
	}
}
