package com.example.camunda.demo.account.service.api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {
  @NotNull
  private UUID id;
  @NotNull
  private String name;
  @NotNull
  private List<AccountDto> accounts;
}
