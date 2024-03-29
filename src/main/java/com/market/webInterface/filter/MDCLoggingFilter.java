package com.market.webInterface.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class MDCLoggingFilter implements Filter {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		final String uuid = UUID.randomUUID().toString();
		MDC.put("requestId", uuid);
		MDC.put("method", httpServletRequest.getMethod());
		MDC.put("domain", httpServletRequest.getServerName());
		MDC.put("api", httpServletRequest.getRequestURI());
		chain.doFilter(request, response);
		MDC.put("status", String.valueOf(httpServletResponse.getStatus()));
		log.info(objectMapper.writeValueAsString(MDC.getCopyOfContextMap()));
		MDC.clear();
	}
}
