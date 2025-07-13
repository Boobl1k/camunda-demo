package com.example.camunda.demo.bpmn.engine.dto.external;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ExchangeRateDto {
  @NotNull
  private String sourceCurrency;
  @NotNull
  private String targetCurrency;
  @NotNull
  private BigDecimal exchangeRate;
}
