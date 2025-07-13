package com.example.camunda.demo.bpmn.engine.dto.external.otp.solving;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class SolveOtpResponse {
  @NotNull
  private UUID otpId;
  @NotNull
  private Boolean solved;
}
