package com.example.camunda.demo.bpmn.engine.service;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Processes;
import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeDto;
import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeRequest;
import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeStartResponse;
import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationResponse;
import com.example.camunda.demo.bpmn.engine.entity.CurrencyExchangeEntity;
import com.example.camunda.demo.bpmn.engine.enumeration.CurrencyExchangeStatus;
import com.example.camunda.demo.bpmn.engine.mapper.CurrencyExchangeMapper;
import com.example.camunda.demo.bpmn.engine.repository.CurrencyExchangeRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyExchangeService {

  private static final String PROCESS_BUSINESS_KEY_TEMPLATE = "%s";

  private final RuntimeService runtimeService;
  private final CurrencyExchangeRepository currencyExchangeRepository;
  private final CurrencyExchangeMapper currencyExchangeMapper;

  public CurrencyExchangeStartResponse handleCurrencyExchangeRequest(
      CurrencyExchangeRequest request
  ) {
    var currencyExchangeEntity = new CurrencyExchangeEntity();
    currencyExchangeEntity.setCreatedAt(LocalDateTime.now());
    currencyExchangeEntity.setStatus(CurrencyExchangeStatus.PENDING);
    currencyExchangeEntity.setRequest(request);
    currencyExchangeEntity = currencyExchangeRepository.save(currencyExchangeEntity);

    log.info(
        "Starting currency exchange with id {}. Request: {}",
        currencyExchangeEntity.getId(),
        request
    );

    var businessKey = PROCESS_BUSINESS_KEY_TEMPLATE.formatted(currencyExchangeEntity.getId());

    var processInstance = runtimeService.createProcessInstanceByKey(Processes.CURRENCY_EXCHANGE)
        .businessKey(businessKey)
        .setVariable(Variables.CURRENCY_EXCHANGE_ID.getName(), currencyExchangeEntity.getId())
        .setVariable(Variables.CURRENCY_EXCHANGE_REQUEST.getName(), request)
        .execute();

    log.info(
        "Process with business key {} has been started. Id: {}",
        businessKey,
        processInstance.getProcessInstanceId()
    );

    return new CurrencyExchangeStartResponse(currencyExchangeEntity.getId());
  }

  public CurrencyExchangeDto getCurrencyExchange(UUID id) {
    return currencyExchangeRepository.findById(id)
        .map(currencyExchangeMapper::map)
        .orElseThrow();
  }

  public List<CurrencyExchangeDto> getAllCurrencyExchanges() {
    return currencyExchangeRepository.findByOrderByCreatedAtDesc()
        .stream()
        .map(currencyExchangeMapper::map)
        .toList();
  }

  public void updateFailed(UUID currencyExchangeId, String errorReason) {
    var entity = currencyExchangeRepository.findById(currencyExchangeId).orElseThrow();
    entity.setStatus(CurrencyExchangeStatus.FAILED);
    entity.setErrorReason(errorReason);
    currencyExchangeRepository.save(entity);
  }

  public void updateSucceeded(UUID currencyExchangeId) {
    var entity = currencyExchangeRepository.findById(currencyExchangeId).orElseThrow();
    entity.setStatus(CurrencyExchangeStatus.SUCCESSFUL);
    currencyExchangeRepository.save(entity);
  }

  public void setTransactionCalculationResponse(
      UUID currencyExchangeId,
      TransactionCalculationResponse transactionCalculationResponse
  ) {
    var entity = currencyExchangeRepository.findById(currencyExchangeId).orElseThrow();
    entity.setTransactionCalculationResponse(transactionCalculationResponse);
    currencyExchangeRepository.save(entity);
  }

}
