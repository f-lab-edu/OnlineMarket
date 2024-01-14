package com.market.exception;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCode {
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST"),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED"),
	FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN"),
	NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND"),
	INTERNAL_SERER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
	private final HttpStatus httpStatus;
	private final String message;
}
