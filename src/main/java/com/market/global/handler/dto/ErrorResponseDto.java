package com.market.global.handler.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponseDto {
	private final String code;
	private final String message;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private final String detail;

	public ErrorResponseDto(String code, String message) {
		this.code = code;
		this.message = message;
		this.detail = "";
	}
}
