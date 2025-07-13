package com.example.camunda.demo.bpmn.engine.mapper;

import com.example.camunda.demo.bpmn.engine.dto.external.ExchangeRateDto;
import com.example.camunda.demo.bpmn.engine.entity.ExchangeRateEntity;
import com.example.camunda.demo.bpmn.engine.mapper.config.MapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ExchangeRateMapper {

  ExchangeRateDto map(ExchangeRateEntity entity);

}
