package com.example.camunda.demo.bpmn.engine.dto.external.otp.solving;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class SolveOtpRequest {
  @NotNull
  private UUID userId;
  @NotNull
  private Integer password;
}
