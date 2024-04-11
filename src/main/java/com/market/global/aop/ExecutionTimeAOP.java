package com.market.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jboss.logging.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeAOP {
	@Around("@within(com.market.global.annotation.ExecutionTimeChecker)")
	public Object dbExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch sw = new StopWatch();
		sw.start();
		Object result = joinPoint.proceed();
		sw.stop();
		long executionTime = sw.getTotalTimeMillis();
		String methodName = joinPoint.getSignature().getName();
		MDC.put(methodName + " executionTime(ms)", executionTime);
		return result;
	}
}
