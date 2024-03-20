package com.market.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ControllerErrorCode implements ErrorCode {
	BAD_REQUEST("BAD_REQUEST", "유효하지 않은 파라미터"),
	UNAUTHORIZED("UNAUTHORIZED", "인증되지 않은 사용자"),
	FORBIDDEN("FORBIDDEN", "요청 거부"),
	NOT_FOUND("NOT_FOUND", "요청한 리소스를 찾을 수 없음"),
	INTERNAL_SERER_ERROR("INTERNAL_SERVER_ERROR", "서버 내부에서 오류 발생"),
	EMPTY_AUTHORIZATION_HEADER("EMPTY_AUTHORIZATION_HEADER", "비어있는 인증 헤더"),
	EMPTY_TOKEN("EMPTY_TOKEN", "비어있는 토큰"),
	INVALID_HEADER_KEY("INVALID_HEADER_KEY", "유효하지 않은 헤더 키");
	private final String code;
	private final String message;
}
