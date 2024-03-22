package com.market.webInterface.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.webInterface.exception.base.WebInterfaceException;

public class InvalidHeaderKeyException extends WebInterfaceException {
	public InvalidHeaderKeyException(ErrorCode error) {
		super(error);
	}
}
