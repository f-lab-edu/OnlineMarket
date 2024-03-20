package com.market.global.exception.base;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class OnlineMarketBaseException extends RuntimeException {
	private final ErrorCode error;
	private final String detail;

	public OnlineMarketBaseException(ErrorCode error) {
		super(error.getMessage());
		this.error = error;
		this.detail = "";
	}

	public OnlineMarketBaseException(ErrorCode error, String detail) {
		super(error.getMessage());
		this.error = error;
		this.detail = detail;
	}
}
