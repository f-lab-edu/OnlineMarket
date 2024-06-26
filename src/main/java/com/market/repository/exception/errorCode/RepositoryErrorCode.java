package com.market.repository.exception.errorCode;

import com.market.global.exception.errorCode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RepositoryErrorCode implements ErrorCode {
	USER_INSERT_FAIL("USER_INSERT_FAIL", "유저 등록 실패");
	private final String code;
	private final String message;
}
