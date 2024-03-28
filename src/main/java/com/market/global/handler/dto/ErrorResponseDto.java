package com.market.global.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponseDto {
	private final String code;
	private final String message;
}
