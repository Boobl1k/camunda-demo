package com.example.camunda.demo.account.service.controller.advice;

import com.example.camunda.demo.account.service.exception.CannotPerformTransactionException;
import com.example.camunda.demo.account.service.exception.EntityNotFoundException;
import com.example.camunda.demo.account.service.exception.InsufficientFundsException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleEntityNotFoundException(
      EntityNotFoundException exception
  ) {
    return handleException(exception);
  }

  @ExceptionHandler(InsufficientFundsException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public Map<String, String> handleInsufficientFundsException(
      InsufficientFundsException exception
  ) {
    return handleException(exception);
  }

  @ExceptionHandler(CannotPerformTransactionException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleCannotPerformTransactionException(
      CannotPerformTransactionException exception
  ) {
    return handleException(exception);
  }

  private Map<String, String> handleException(RuntimeException exception) {
    log.warn("Exception caught in GlobalExceptionHandler", exception);
    Map<String, String> errorBody = new HashMap<>();
    errorBody.put("errorMessage", exception.getMessage());
    return errorBody;
  }

}
