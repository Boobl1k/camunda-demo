package com.example.camunda.demo.bpmn.engine.delegate;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import com.example.camunda.demo.bpmn.engine.mapper.TransactionCalculationMapper;
import com.example.camunda.demo.bpmn.engine.service.CurrencyExchangeService;
import com.example.camunda.demo.bpmn.engine.service.mock.TransactionCalculationMockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionCalculationDelegate extends JavaDelegateWrapper {
  private final TransactionCalculationMockService transactionCalculationMockService;
  private final TransactionCalculationMapper transactionCalculationMapper;
  private final CurrencyExchangeService currencyExchangeService;

  @Override
  protected void execute(DelegateExecutionWrapper execution) {
    log.info("Transaction calculation started");

    var currencyExchangeRequest = execution.getVariable(Variables.CURRENCY_EXCHANGE_REQUEST);
    var transactionCalculationRequest = transactionCalculationMapper.map(currencyExchangeRequest);

    var transactionCalculationResponse =
        transactionCalculationMockService.calculateTransaction(transactionCalculationRequest);

    execution.setVariable(
        Variables.TRANSACTION_CALCULATION_RESPONSE,
        transactionCalculationResponse
    );

    var currencyExchangeId = execution.getVariable(Variables.CURRENCY_EXCHANGE_ID);
    currencyExchangeService.setTransactionCalculationResponse(
        currencyExchangeId,
        transactionCalculationResponse
    );

    log.info("Transaction calculation completed");
  }
}
