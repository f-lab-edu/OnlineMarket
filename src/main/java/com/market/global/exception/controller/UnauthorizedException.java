package com.market.global.exception.controller;

import com.market.global.exception.errorCode.ErrorCode;

public class UnauthorizedException extends ControllerException {
	public UnauthorizedException(ErrorCode error) {
		super(error);
	}

	public UnauthorizedException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
