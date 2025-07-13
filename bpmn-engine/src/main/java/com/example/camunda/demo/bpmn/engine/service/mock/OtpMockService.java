package com.example.camunda.demo.bpmn.engine.service.mock;

import com.example.camunda.demo.bpmn.engine.dto.external.otp.creation.CreateOtpRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.creation.CreateOtpResponse;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.solving.SolveOtpRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.solving.SolveOtpResponse;
import com.example.camunda.demo.bpmn.engine.mapper.OtpMapper;
import com.example.camunda.demo.bpmn.engine.messaging.producer.OtpResultMockProducer;
import com.example.camunda.demo.bpmn.engine.repository.OtpRepository;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpMockService {
  private final OtpMapper otpMapper;
  private final OtpRepository otpRepository;
  private final OtpResultMockProducer otpResultMockProducer;
  private final Random random = new Random();

  public CreateOtpResponse createOtp(CreateOtpRequest request) {
    var password = random.nextInt(1_000, 10_000);
    var entity = otpRepository
        .findByUserId(request.getUserId())
        .map(existing -> {
          existing.setPassword(password);
          existing.setSolved(false);
          return existing;
        }).orElseGet(() ->
            otpMapper.map(request, password)
        );

    otpRepository.save(entity);
    log.info("Created OTP with password {} for user {}", password, request.getUserId());

    return otpMapper.map(entity);
  }

  public SolveOtpResponse solveOtp(SolveOtpRequest request) {
    var entity = otpRepository.findByUserId(request.getUserId()).orElseThrow();

    var isPasswordCorrect = entity.getPassword().equals(request.getPassword());

    entity.setSolved(isPasswordCorrect);
    otpRepository.save(entity);

    var otpResult = otpMapper.mapToResult(entity, isPasswordCorrect);
    otpResultMockProducer.sendResponse(otpResult);

    if (isPasswordCorrect) {
      log.info("Solved OTP for user {}", request.getUserId());
    } else {
      log.warn("OTP failed for user {}. Password is not correct", request.getUserId());
    }

    return otpMapper.mapToSolveResponse(entity);
  }
}
