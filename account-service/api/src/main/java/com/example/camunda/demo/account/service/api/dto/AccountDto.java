package com.example.camunda.demo.account.service.api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class AccountDto {
  @NotNull
  private UUID id;
  @NotNull
  private String currency;
  @NotNull
  private Long amount;
}
