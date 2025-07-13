package com.example.camunda.demo.bpmn.engine.controller;

import com.example.camunda.demo.bpmn.engine.dto.external.otp.creation.CreateOtpRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.creation.CreateOtpResponse;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.solving.SolveOtpRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.solving.SolveOtpResponse;
import com.example.camunda.demo.bpmn.engine.service.mock.OtpMockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OtpMockController {
  private final OtpMockService otpMockService;

  @RequestMapping(
      method = RequestMethod.POST,
      value = "/api/v1/otp"
  )
  public ResponseEntity<CreateOtpResponse> createOtp(@RequestBody CreateOtpRequest request) {
    return ResponseEntity.ok(otpMockService.createOtp(request));
  }

  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/api/v1/otp/solve"
  )
  public ResponseEntity<SolveOtpResponse> solveOtp(@RequestBody SolveOtpRequest request) {
    return ResponseEntity.ok(otpMockService.solveOtp(request));
  }
}
