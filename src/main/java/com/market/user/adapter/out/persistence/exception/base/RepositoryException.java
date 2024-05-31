package com.market.user.adapter.out.persistence.exception.base;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

public class RepositoryException extends OnlineMarketBaseException {
	public RepositoryException(ErrorCode error) {
		super(error);
	}
}
