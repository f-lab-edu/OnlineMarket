package com.market.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.market.global.handler.dto.ErrorResponseDto;
import com.market.webInterface.exception.UnauthorizedException;
import com.market.webInterface.exception.errorCode.WebInterfaceErrorCode;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ErrorResponseDto(WebInterfaceErrorCode.BAD_REQUEST.getCode(),
			WebInterfaceErrorCode.BAD_REQUEST.getMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ErrorResponseDto handleUnauthorizedException(UnauthorizedException e) {
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage(), e.getDetail());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserCreateFailException.class)
	public ErrorResponseDto handleUserCreateFailException(UserCreateFailException e) {
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage(), e.getDetail());
	}
}
