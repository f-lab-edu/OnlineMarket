package com.market.global.exception;

public class UnauthorizedException extends RuntimeException {
	private static final String message = "인증되지 않은 사용자입니다.";

	public UnauthorizedException() {
		super(message);
	}
}
