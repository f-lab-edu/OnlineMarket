package com.market.webInterface.exception.base;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.ErrorCode;

public class WebInterfaceException extends OnlineMarketBaseException {
	public WebInterfaceException(ErrorCode error) {
		super(error);
	}
}
