package com.market.user.adapter.out.persistence.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.user.adapter.out.persistence.exception.base.RepositoryException;

public class UserInsertFailException extends RepositoryException {
	public UserInsertFailException(ErrorCode error) {
		super(error);
	}
}
