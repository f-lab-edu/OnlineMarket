package com.market.user.application.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.user.application.exception.base.ApplicationException;

public class DuplicatedUserEmailException extends ApplicationException {
	public DuplicatedUserEmailException(ErrorCode error) {
		super(error);
	}
}
