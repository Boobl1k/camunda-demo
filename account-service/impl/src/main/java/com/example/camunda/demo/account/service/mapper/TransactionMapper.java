package com.example.camunda.demo.account.service.mapper;

import com.example.camunda.demo.account.service.api.dto.PerformTransactionRequest;
import com.example.camunda.demo.account.service.api.dto.PerformTransactionResponse;
import com.example.camunda.demo.account.service.entity.AccountEntity;
import com.example.camunda.demo.account.service.mapper.config.MapperConfig;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface TransactionMapper {

  @Mapping(target = "currentAmount", expression = "java(account.getAmount())")
  PerformTransactionResponse map(PerformTransactionRequest request, @Context AccountEntity account);

}
