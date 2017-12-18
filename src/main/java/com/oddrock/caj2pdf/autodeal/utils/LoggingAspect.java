package com.oddrock.caj2pdf.autodeal.utils;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 用来打日志的切面
@Aspect
@Component
public class LoggingAspect {
	private static Logger logger = Logger.getLogger(LoggingAspect.class);
	
    @Around("execution(public * com.oddrock.caj2pdf.autodeal.Plugins4*.*(..))")
    private void arithmeticDoLog(ProceedingJoinPoint jp) throws Throwable {
    	logger.warn(jp.getTarget().getClass().getName()
    			+"."+jp.getSignature().getName()+"(): start......");
    	jp.proceed();
    	logger.warn(jp.getTarget().getClass().getName()
    			+"."+jp.getSignature().getName()+"(): end");
    }
}