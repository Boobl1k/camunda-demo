package com.example.camunda.demo.account.service.api.dto;

import com.example.camunda.demo.account.service.api.enumeration.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PerformTransactionRequest {
  @NotNull
  private String currency;
  @NotNull
  private TransactionType transactionType;
  @NotNull
  private Long amount;
}
