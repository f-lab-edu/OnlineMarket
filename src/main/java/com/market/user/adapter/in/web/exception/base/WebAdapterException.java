package com.market.user.adapter.in.web.exception.base;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

public class WebAdapterException extends OnlineMarketBaseException {
	public WebAdapterException(ErrorCode error) {
		super(error);
	}
}
