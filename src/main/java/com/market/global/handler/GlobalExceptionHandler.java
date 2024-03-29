package com.market.global.handler;

import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.application.exception.base.ApplicationException;
import com.market.global.exception.base.OnlineMarketBaseException;
import com.market.global.exception.errorCode.OnlineMarketBaseErrorCode;
import com.market.global.handler.dto.ErrorResponseDto;
import com.market.repository.exception.base.RepositoryException;
import com.market.webInterface.exception.UnauthorizedException;
import com.market.webInterface.exception.base.WebInterfaceException;
import com.market.webInterface.exception.errorCode.WebInterfaceErrorCode;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorResponseDto handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		return makeErrorResponse(new WebInterfaceException(WebInterfaceErrorCode.METHOD_NOT_ALLOWED));
	}

	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ErrorResponseDto handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
		return makeErrorResponse(new WebInterfaceException(WebInterfaceErrorCode.UNSUPPORTED_MEDIA_TYPE));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return makeErrorResponse(new WebInterfaceException(WebInterfaceErrorCode.INVALID_PARAMETER));
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ErrorResponseDto handleNoHandlerFoundException(NoHandlerFoundException e) {
		return makeErrorResponse(new WebInterfaceException(WebInterfaceErrorCode.NOT_FOUND));
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
		return makeErrorResponse(new OnlineMarketBaseException(OnlineMarketBaseErrorCode.INTERNAL_SERER_ERROR));
	}

	private ErrorResponseDto makeErrorResponse(OnlineMarketBaseException e) {
		logging(e);
		return new ErrorResponseDto(e.getError().getCode(), e.getError().getMessage());
	}

	private void logging(Exception ex) {
		MDC.put("errorMessage", ex.getMessage());
		try {
			log.error(objectMapper.writeValueAsString(MDC.getCopyOfContextMap()));
		} catch (JsonProcessingException e) {
			log.error("requestId = {}, errorMessage = JsonProcessingException", MDC.get("requestId"));
		}
	}
}
