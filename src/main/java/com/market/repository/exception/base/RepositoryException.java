package com.market.repository.exception.base;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class RepositoryException extends OnlineMarketBaseException {
	public RepositoryException(ErrorCode error) {
		super(error);
	}

	public RepositoryException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
