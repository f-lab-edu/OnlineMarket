package com.market.user.adapter.in.web.exception.errorCode;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WebAdapterErrorCode implements ErrorCode {
	INVALID_PARAMETER("INVALID_PARAMETER", "유효하지 않은 파라미터"),
	METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "허용되지 않은 HTTP 메소드"),
	UNSUPPORTED_MEDIA_TYPE("UNSUPPORTED_MEDIA_TYPE", "지원되지 않는 미디어 타입"),
	UNAUTHORIZED("UNAUTHORIZED", "인증되지 않은 사용자"),
	FORBIDDEN("FORBIDDEN", "요청 거부"),
	NOT_FOUND("NOT_FOUND", "요청한 리소스를 찾을 수 없음");
	private final String code;
	private final String message;
}
