package com.market.global.exception.controller;

import com.market.global.exception.errorCode.ErrorCode;

public class InvalidHeaderKeyException extends ControllerException {
	public InvalidHeaderKeyException(ErrorCode error) {
		super(error);
	}

	public InvalidHeaderKeyException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
