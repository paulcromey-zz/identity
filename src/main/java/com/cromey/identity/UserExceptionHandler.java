package com.cromey.identity;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.cromey.identity.controller.UserController;
import com.cromey.identity.repository.UserRepository;
import com.cromey.identity.service.UserService;

@ControllerAdvice(assignableTypes = { 
    UserController.class, 
    UserService.class, 
    UserRepository.class })
public class UserExceptionHandler {

  /**
   * returns error response for resource constraints.
   * 
   * @param ex web exchange bind exception
   * @return exception response
   */
  @ExceptionHandler(WebExchangeBindException.class)
  public ResponseEntity<Object> handleWebExchangeBindException(WebExchangeBindException ex) {
      Map<String, String> errors = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach(error -> {
          String fieldName = ((FieldError) error).getField();
          String errorMessage = error.getDefaultMessage();
          errors.put(fieldName, errorMessage);
      });
      return ResponseEntity.badRequest().body(errors);
  }

}
