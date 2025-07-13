package com.example.camunda.demo.bpmn.engine.mapper;

import com.example.camunda.demo.bpmn.engine.dto.external.otp.OtpResultDto;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.creation.CreateOtpRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.creation.CreateOtpResponse;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.solving.SolveOtpResponse;
import com.example.camunda.demo.bpmn.engine.entity.OtpEntity;
import com.example.camunda.demo.bpmn.engine.mapper.config.MapperConfig;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OtpMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "solved", constant = "false")
  @Mapping(target = "password", expression = "java(password)")
  OtpEntity map(CreateOtpRequest request, @Context int password);

  @Mapping(target = "otpId", source = "id")
  CreateOtpResponse map(OtpEntity entity);

  @Mapping(target = "otpId", source = "id")
  SolveOtpResponse mapToSolveResponse(OtpEntity entity);

  @Mapping(target = "otpId", source = "entity.id")
  OtpResultDto mapToResult(OtpEntity entity, boolean solved);
}
