package com.example.camunda.demo.account.service.mapper.config;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.ERROR;

import org.mapstruct.InjectionStrategy;

@org.mapstruct.MapperConfig(
    componentModel = SPRING,
    unmappedTargetPolicy = ERROR,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface MapperConfig {
}
