package com.cromey.identity.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserLogger {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut("within(com.cromey.identity..*)")
  public void methods() {
    // deliberately empty
  }
  
  /**
   * executes before class methods.
   * 
   * @param joinPoint the class
   */
  @Before("methods()")
  public void before(JoinPoint joinPoint) {

    StringBuilder sb = new StringBuilder().append("Entering class ")
        .append(joinPoint.getTarget().getClass().getSimpleName()).append(" method ")
        .append(joinPoint.getSignature().getName());
    
    String message = sb.toString();
    
    logger.debug(message);

  }

  /**
   * executes after class methods.
   * 
   * @param joinPoint the class
   */
  @After("methods()")
  public void after(JoinPoint joinPoint) {

    StringBuilder sb = new StringBuilder().append("Exiting class ")
        .append(joinPoint.getTarget().getClass().getSimpleName()).append(" method ")
        .append(joinPoint.getSignature().getName());
    
    String message = sb.toString();
    
    logger.debug(message);

  }

}
