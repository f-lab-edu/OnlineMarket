package com.market.user.adapter.in.web.exception;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.user.adapter.in.web.exception.base.WebAdapterException;

public class UnauthorizedException extends WebAdapterException {
	public UnauthorizedException(ErrorCode error) {
		super(error);
	}
}
