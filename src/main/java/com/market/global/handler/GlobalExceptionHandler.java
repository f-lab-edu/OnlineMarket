package com.market.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.market.application.exception.base.ApplicationException;
import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.handler.dto.ErrorResponseDto;
import com.market.repository.exception.base.RepositoryException;
import com.market.webInterface.exception.UnauthorizedException;
import com.market.webInterface.exception.base.WebInterfaceException;
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
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApplicationException.class)
	public ErrorResponseDto handleApplicationException(ApplicationException e) {
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RepositoryException.class)
	public ErrorResponseDto handleRepositoryException(RepositoryException e) {
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebInterfaceException.class)
	public ErrorResponseDto handleWebInterfaceException(WebInterfaceException e) {
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(OnlineMarketBaseException.class)
	public ErrorResponseDto handleBaseException(OnlineMarketBaseException e) {
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}
}
