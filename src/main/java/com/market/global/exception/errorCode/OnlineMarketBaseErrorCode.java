package com.market.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OnlineMarketBaseErrorCode implements ErrorCode {
	INTERNAL_SERER_ERROR("INTERNAL_SERVER_ERROR", "서버 내부에서 오류 발생");
	private final String code;
	private final String message;
}
