package com.market.webInterface.controller.dto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.market.webInterface.controller.dto.annotation.validator.PasswordValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

@NotBlank
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
	String message() default "비밀번호는 영문, 숫자, 특수기호를 모두 포함한 8글자 이상 20자 이하이어야 합니다.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
