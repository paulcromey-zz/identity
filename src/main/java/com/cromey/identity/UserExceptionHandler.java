package com.cromey.identity;

import com.cromey.identity.controller.UserController;
import com.cromey.identity.repository.UserRepository;
import com.cromey.identity.service.UserService;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = { 
    UserController.class, 
    UserService.class, 
    UserRepository.class })
public class UserExceptionHandler {

  /**
   * returns error response for resource constraints.
   * 
   * @param constraintViolationException constraint exception
   * @return exception response
   */
  @ExceptionHandler(value = { ConstraintViolationException.class })
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleResourceConstraintException(
      ConstraintViolationException constraintViolationException) {
    Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
    StringBuilder strBuilder = new StringBuilder();
    for (ConstraintViolation<?> violation : violations) {
      strBuilder.append(violation.getMessage());
    }
    ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(), strBuilder.toString(), "");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

}
