package com.example.camunda.demo.bpmn.engine.mapper;

import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationResponse;
import com.example.camunda.demo.bpmn.engine.mapper.config.MapperConfig;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TransactionCalculationMapper {
  TransactionCalculationRequest map(CurrencyExchangeRequest currencyExchangeRequest);

  @Mapping(target = "target.amount", expression = "java(targetAmount)")
  TransactionCalculationResponse map(
      TransactionCalculationRequest request,
      @Context Long targetAmount
  );
}
