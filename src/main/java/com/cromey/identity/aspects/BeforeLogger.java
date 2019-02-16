package com.cromey.identity.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeLogger {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.cromey.identity..*)")
    public void methods() {};
	
	@Before("methods()")
	public void before(JoinPoint joinPoint) throws Throwable {
		
		logger.debug(String.format("Entering class %s method %s", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName()));
		
	}
	
}