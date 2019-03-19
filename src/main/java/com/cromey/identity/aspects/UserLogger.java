package com.cromey.identity.aspects;

import java.util.concurrent.TimeUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
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
   * @param jp the join point
   */
  @Before("methods()")
  public void before(JoinPoint jp) {

    log("Entering ", jp);

  }
  
  /**
   * executes around class methods.
   * 
   * @param pjp the proceeding join point
   */
  @Around("methods()")
  public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
    long start = System.nanoTime();
    Object retval = pjp.proceed();
    long end = System.nanoTime();
    long duration = TimeUnit.NANOSECONDS.toMillis(end - start);
    log("Execution ", duration);
    return retval;
  }

  /**
   * executes after class methods.
   * 
   * @param jp the join point
   */
  @After("methods()")
  public void after(JoinPoint jp) {
    
    log("Exiting ", jp);

  }
  
  private void log(String state, JoinPoint jp) {
    
    StringBuilder sb = new StringBuilder()
        .append(state)
        .append(jp.getTarget().getClass().getSimpleName())
        .append(" ")
        .append(jp.getSignature().getName());
    
    String message = sb.toString();
    
    logger.debug(message);
    
  }
  
  private void log(String state, Long duration) {
    
    StringBuilder sb = new StringBuilder()
        .append(state)
        .append(duration)
        .append(" ms");
    
    String message = sb.toString();
    
    logger.debug(message);
    
  }

}
