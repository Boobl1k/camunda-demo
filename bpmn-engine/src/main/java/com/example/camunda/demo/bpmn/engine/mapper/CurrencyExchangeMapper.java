package com.example.camunda.demo.bpmn.engine.mapper;

import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeDto;
import com.example.camunda.demo.bpmn.engine.entity.CurrencyExchangeEntity;
import com.example.camunda.demo.bpmn.engine.mapper.config.MapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CurrencyExchangeMapper {

  CurrencyExchangeDto map(CurrencyExchangeEntity entity);

}
