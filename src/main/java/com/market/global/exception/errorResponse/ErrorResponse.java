package com.market.global.exception.errorResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {
	private final String code;
	private final String message;
	// @JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String detail;

	public ErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
		this.detail = "";
	}
}
