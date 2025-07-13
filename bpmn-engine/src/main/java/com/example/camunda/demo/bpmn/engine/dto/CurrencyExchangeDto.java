package com.example.camunda.demo.bpmn.engine.dto;

import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationResponse;
import com.example.camunda.demo.bpmn.engine.enumeration.CurrencyExchangeStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class CurrencyExchangeDto {
  @NotNull
  private UUID id;
  @NotNull
  private CurrencyExchangeStatus status;
  @Nullable
  private String errorReason;
  @NotNull
  private CurrencyExchangeRequest request;
  @Nullable
  private TransactionCalculationResponse transactionCalculationResponse;
}
