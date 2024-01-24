package com.market.error;

import org.springframework.web.bind.annotation.RestControllerAdvice;
//
// import com.market.auth.exception.UnauthorizedException;

@RestControllerAdvice
public class ErrorController {
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// @ExceptionHandler(MethodArgumentNotValidException.class)
	// public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
	// 	return new ErrorResponse(ErrorCode.BAD_REQUEST, e.getMessage());
	// }
	//
	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	// @ExceptionHandler(IllegalArgumentException.class)
	// public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
	// 	return new ErrorResponse(ErrorCode.INTERNAL_SERER_ERROR, e.getMessage());
	// }
	//
	// @ResponseStatus(HttpStatus.UNAUTHORIZED)
	// @ExceptionHandler(UnauthorizedException.class)
	// public ErrorResponse handleUnauthorizedException(UnauthorizedException e) {
	// 	return new ErrorResponse(ErrorCode.UNAUTHORIZED, e.getMessage());
	// }
	//
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// @ExceptionHandler(RuntimeException.class)
	// public ErrorResponse handleRuntimeException(RuntimeException e) {
	// 	return new ErrorResponse(ErrorCode.BAD_REQUEST, e.getMessage());
	// }
	//
	// @ResponseStatus(HttpStatus.BAD_REQUEST)
	// @ExceptionHandler(Exception.class)
	// public ErrorResponse handleException(Exception e) {
	// 	return new ErrorResponse(ErrorCode.BAD_REQUEST, e.getMessage());
	// }
}
