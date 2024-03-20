package com.market.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.market.global.exception.application.UserCreateFailException;
import com.market.global.exception.controller.UnauthorizedException;
import com.market.global.exception.errorCode.ControllerErrorCode;
import com.market.global.exception.errorResponse.ErrorResponse;

@RestControllerAdvice
public class ErrorController {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return new ErrorResponse(ControllerErrorCode.BAD_REQUEST.getCode(),
			ControllerErrorCode.BAD_REQUEST.getMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
		return new ErrorResponse(e.getError().getCode(), e.getError().getMessage(), e.getDetail());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserCreateFailException.class)
	public ErrorResponse handleUserCreateFailException(UserCreateFailException e) {
		return new ErrorResponse(e.getError().getCode(), e.getError().getMessage(), e.getDetail());
	}
}
