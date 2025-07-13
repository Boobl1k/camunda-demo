package com.example.camunda.demo.account.service.setting;

import lombok.Getter;
import lombok.Setter;

public class TransactionsHandlingSetting {
  @Getter
  @Setter
  private static Value value = Value.HANDLE_SUCCESSFULLY;

  public static void reset() {
    value = Value.HANDLE_SUCCESSFULLY;
  }

  public enum Value {
    HANDLE_SUCCESSFULLY,
    HANDLE_NEXT_DEPOSIT_WITH_FAILURE
  }
}
