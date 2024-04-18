package com.market.webInterface.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.webInterface.exception.base.WebInterfaceException;

public class UnauthorizedException extends WebInterfaceException {
	public UnauthorizedException(ErrorCode error) {
		super(error);
	}
}
