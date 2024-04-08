package com.market.webInterface.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.market.global.exception.errorCode.ErrorCode;
import com.market.global.exception.errorCode.OnlineMarketBaseErrorCode;
import com.market.global.handler.dto.ErrorResponseDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class MDCLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws IOException {
		try {
			final String uuid = UUID.randomUUID().toString();
			MDC.put("requestId", uuid);
			MDC.put("method", request.getMethod());
			MDC.put("domain", request.getServerName());
			MDC.put("api", request.getRequestURI());
			filterChain.doFilter(request, response);
			MDC.put("status", String.valueOf(response.getStatus()));
		} catch (Exception e) {
			setErrorResponse(response);
		} finally {
			MDC.clear();
		}
	}

	private void setErrorResponse(HttpServletResponse response) throws IOException {
		ErrorCode errorCode = OnlineMarketBaseErrorCode.INTERNAL_SERER_ERROR;
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ErrorResponseDto errorResponse = new ErrorResponseDto(errorCode.getCode(), errorCode.getMessage());
		// response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}
}
