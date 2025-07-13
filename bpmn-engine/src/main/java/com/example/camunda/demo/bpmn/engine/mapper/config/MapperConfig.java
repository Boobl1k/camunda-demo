package com.example.camunda.demo.bpmn.engine.mapper.config;

import org.mapstruct.InjectionStrategy;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.ERROR;

@org.mapstruct.MapperConfig(
    componentModel = SPRING,
    unmappedTargetPolicy = ERROR,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface MapperConfig {
}
