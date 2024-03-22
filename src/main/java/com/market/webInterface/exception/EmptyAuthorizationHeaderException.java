package com.market.webInterface.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.webInterface.exception.base.WebInterfaceException;

public class EmptyAuthorizationHeaderException extends WebInterfaceException {
	public EmptyAuthorizationHeaderException(ErrorCode error) {
		super(error);
	}
}
