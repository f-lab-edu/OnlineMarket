package com.market.global.handler.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponseDto {
	private final String code;
	private final String message;
}
