package com.market.global.exception.application;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class UserCreateFailException extends ApplicationException {
	public UserCreateFailException(ErrorCode error) {
		super(error);
	}

	public UserCreateFailException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
