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

  private static final String BLANK_SPACE = " ";
  private static final String EXITING = "Exiting ";
  private static final String ENTERING = "Entering ";
  
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

    log(ENTERING, joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());

  }

  /**
   * executes after class methods.
   * 
   * @param joinPoint the class
   */
  @After("methods()")
  public void after(JoinPoint joinPoint) {
    
    log(EXITING, joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());

  }
  
  private void log(String state, String className, String methodName) {
    
    StringBuilder sb = new StringBuilder()
        .append(state)
        .append(className)
        .append(BLANK_SPACE)
        .append(methodName);
    
    String message = sb.toString();
    
    logger.debug(message);
    
  }

}
