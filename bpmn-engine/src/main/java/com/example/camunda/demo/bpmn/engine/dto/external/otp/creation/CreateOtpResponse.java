package com.example.camunda.demo.bpmn.engine.dto.external.otp.creation;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;

@Data
public class CreateOtpResponse {
  @NotNull
  private UUID userId;
  @NotNull
  private UUID otpId;
}
