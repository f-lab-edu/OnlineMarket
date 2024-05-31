package com.market.global.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.OnlineMarketBaseErrorCode;
import com.market.global.handler.dto.ErrorResponseDto;
import com.market.user.adapter.in.web.exception.UnauthorizedException;
import com.market.user.adapter.in.web.exception.base.WebAdapterException;
import com.market.user.adapter.in.web.exception.errorCode.WebAdapterErrorCode;
import com.market.user.adapter.out.persistence.exception.base.RepositoryException;
import com.market.user.application.exception.base.ApplicationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger("errorLogger");

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorResponseDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return makeErrorResponse(new WebAdapterException(WebAdapterErrorCode.METHOD_NOT_ALLOWED));
	}

	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ErrorResponseDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		return makeErrorResponse(new WebAdapterException(WebAdapterErrorCode.UNSUPPORTED_MEDIA_TYPE));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return makeErrorResponse(new WebAdapterException(WebAdapterErrorCode.INVALID_PARAMETER));
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ErrorResponseDto handleNoHandlerFoundException(NoHandlerFoundException e) {
		return makeErrorResponse(new WebAdapterException(WebAdapterErrorCode.NOT_FOUND));
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ErrorResponseDto handleUnauthorizedException(UnauthorizedException e) {
		return makeErrorResponse(e);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ApplicationException.class)
	public ErrorResponseDto handleApplicationException(ApplicationException e) {
		return makeErrorResponse(e);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RepositoryException.class)
	public ErrorResponseDto handleRepositoryException(RepositoryException e) {
		return makeErrorResponse(e);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorResponseDto handleBaseException(Exception e) {
		return makeErrorResponse(new OnlineMarketBaseException(OnlineMarketBaseErrorCode.INTERNAL_SERER_ERROR), e);
	}

	private ErrorResponseDto makeErrorResponse(OnlineMarketBaseException e) {
		logging(e);
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	private ErrorResponseDto makeErrorResponse(OnlineMarketBaseException e, Exception cause) {
		logging(e);
		logger.error("{}", MDC.get("requestId"), cause);
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	private void logging(OnlineMarketBaseException ex) {
		MDC.put("errorMessage", ex.getError().getMessage());
		MDC.put("errorCode", ex.getError().getCode());
	}
}
