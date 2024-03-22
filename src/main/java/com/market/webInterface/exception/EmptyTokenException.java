package com.market.webInterface.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.webInterface.exception.base.WebInterfaceException;

public class EmptyTokenException extends WebInterfaceException {
	public EmptyTokenException(ErrorCode error) {
		super(error);
	}
}
