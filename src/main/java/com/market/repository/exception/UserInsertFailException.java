package com.market.repository.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.repository.exception.base.RepositoryException;

public class UserInsertFailException extends RepositoryException {
	public UserInsertFailException(ErrorCode error) {
		super(error);
	}
}
