package com.market.application.exception;

import com.market.application.exception.base.ApplicationException;
import com.market.global.exception.errorCode.ErrorCode;

public class DuplicatedUserEmailException extends ApplicationException {
	public DuplicatedUserEmailException(ErrorCode error) {
		super(error);
	}
}
