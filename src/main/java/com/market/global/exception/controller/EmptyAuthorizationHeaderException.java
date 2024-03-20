package com.market.global.exception.controller;

import com.market.global.exception.errorCode.ErrorCode;

public class EmptyAuthorizationHeaderException extends ControllerException {
	public EmptyAuthorizationHeaderException(ErrorCode error) {
		super(error);
	}

	public EmptyAuthorizationHeaderException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
