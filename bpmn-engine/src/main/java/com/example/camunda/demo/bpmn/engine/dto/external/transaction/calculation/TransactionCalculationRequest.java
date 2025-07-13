package com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionCalculationRequest {

  @NotNull
  private TransactionCalculationRequestSource source;
  @NotNull
  private TransactionCalculationRequestTarget target;
  @NotNull
  @Positive
  private BigDecimal exchangeRate;

  @Data
  public static class TransactionCalculationRequestSource {
    @NotNull
    @Min(1)
    @Max(1_000_000_000)
    private Long amount;
    @NotEmpty
    private String currency;
  }

  @Data
  public static class TransactionCalculationRequestTarget {
    @NotEmpty
    private String currency;
  }
}
