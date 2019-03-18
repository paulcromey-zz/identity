package com.cromey.identity.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterLogger {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Pointcut("within(com.cromey.identity..*)")
  public void methods() {
    // deliberately empty
  }

  /**
   * executes after class methods.
   * 
   * @param joinPoint the class
   */
  @After("methods()")
  public void after(JoinPoint joinPoint) {

    String message = "Exiting class " + joinPoint.getTarget().getClass().getSimpleName() 
        + " method " + joinPoint.getSignature().getName();
    logger.debug(message);

  }

}
