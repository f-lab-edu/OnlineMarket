package com.market.global.exception.repository;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class UserInsertFailException extends RepositoryException {
	public UserInsertFailException(ErrorCode error) {
		super(error);
	}

	public UserInsertFailException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
