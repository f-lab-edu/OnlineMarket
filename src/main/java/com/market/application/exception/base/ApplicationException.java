package com.market.application.exception.base;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

public class ApplicationException extends OnlineMarketBaseException {
	public ApplicationException(ErrorCode error) {
		super(error);
	}
}
