package com.example.camunda.demo.account.service.exception;

public class InsufficientFundsException extends RuntimeException {

  private static final String MESSAGE = "Недостаточно средств на счете";

  public InsufficientFundsException() {
    super(MESSAGE);
  }

}
