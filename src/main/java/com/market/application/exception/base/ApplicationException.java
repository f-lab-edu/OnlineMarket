package com.market.application.exception.base;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class ApplicationException extends OnlineMarketBaseException {
	public ApplicationException(ErrorCode error) {
		super(error);
	}

	public ApplicationException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
