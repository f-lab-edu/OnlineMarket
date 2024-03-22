package com.market.application.exception;

import com.market.application.exception.base.ApplicationException;
import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class DuplicatedUserEmailException extends ApplicationException {
	public DuplicatedUserEmailException(ErrorCode error) {
		super(error);
	}

	public DuplicatedUserEmailException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
