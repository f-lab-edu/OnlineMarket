package com.market.global.exception.controller;

import com.market.global.exception.errorCode.ErrorCode;

public class EmptyTokenException extends ControllerException {
	public EmptyTokenException(ErrorCode error) {
		super(error);
	}

	public EmptyTokenException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
