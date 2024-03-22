package com.market.application.exception.errorCode;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ApplicationErrorCode implements ErrorCode {
	INTERNAL_APPLICATION_ERROR("INTERNAL_APPLICATION_ERROR", "Application 내부에서 에러"),
	DUPLICATED_USER_EMAIL("DUPLICATED_USER_EMAIL", "Email 중복");
	private final String code;
	private final String message;
}
