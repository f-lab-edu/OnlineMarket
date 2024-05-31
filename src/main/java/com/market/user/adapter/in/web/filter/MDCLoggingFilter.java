package com.market.user.adapter.in.web.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCLoggingFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger("accessLogger");

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws IOException, ServletException {
		StopWatch sw = new StopWatch();
		sw.start();
		final String uuid = UUID.randomUUID().toString();
		MDC.put("requestId", uuid);
		MDC.put("method", request.getMethod());
		MDC.put("url", request.getServerName());
		MDC.put("path", request.getRequestURI());
		try {
			filterChain.doFilter(request, response);
		} finally {
			MDC.put("status", String.valueOf(response.getStatus()));
			sw.stop();
			long executionTime = sw.getTotalTimeMillis();
			MDC.put("total executionTime(ms)", String.valueOf(executionTime));
			logger.info("access.log");
			MDC.clear();
		}
	}
}
