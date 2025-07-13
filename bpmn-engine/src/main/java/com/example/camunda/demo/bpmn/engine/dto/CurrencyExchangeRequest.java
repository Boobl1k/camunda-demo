package com.example.camunda.demo.bpmn.engine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class CurrencyExchangeRequest {
  @NotNull
  private UUID userId;
  @NotNull
  private CurrencyExchangeSource source;
  @NotNull
  private CurrencyExchangeTarget target;
  @NotNull
  @Positive
  @Schema(example = "0.012")
  private BigDecimal exchangeRate;

  @Data
  public static class CurrencyExchangeSource {
    @NotNull
    private UUID accountId;
    @NotNull
    @Min(1)
    @Max(1_000_000_000)
    @Schema(example = "500000")
    private Long amount;
    @NotEmpty
    @Schema(example = "RUB")
    private String currency;
  }

  @Data
  public static class CurrencyExchangeTarget {
    @NotNull
    private UUID accountId;
    @NotEmpty
    @Schema(example = "USD")
    private String currency;
  }
}
