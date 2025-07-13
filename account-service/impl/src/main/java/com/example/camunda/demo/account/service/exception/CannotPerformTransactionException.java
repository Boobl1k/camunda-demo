package com.example.camunda.demo.account.service.exception;

public class CannotPerformTransactionException extends RuntimeException {

  private static final String MESSAGE = "Недостаточно средств на счете";

  public CannotPerformTransactionException() {
    super(MESSAGE);
  }
}
