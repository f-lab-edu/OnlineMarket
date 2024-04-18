package com.market.global.exception.base;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class OnlineMarketBaseException extends RuntimeException {
	private final ErrorCode error;

	public OnlineMarketBaseException(ErrorCode error) {
		super(error.getMessage());
		this.error = error;
	}
}
