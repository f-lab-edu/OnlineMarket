package com.market.global.exception.controller;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;

@Getter
public class ControllerException extends OnlineMarketBaseException {
	public ControllerException(ErrorCode error) {
		super(error);
	}

	public ControllerException(ErrorCode error, String detail) {
		super(error, detail);
	}
}
