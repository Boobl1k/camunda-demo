package com.example.camunda.demo.bpmn.engine.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyExchangeStartResponse {
  @NotNull
  private UUID currencyExchangeId;
}
