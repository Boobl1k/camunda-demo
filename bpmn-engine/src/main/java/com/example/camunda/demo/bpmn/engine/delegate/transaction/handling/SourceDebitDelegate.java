package com.example.camunda.demo.bpmn.engine.delegate.transaction.handling;

import com.example.camunda.demo.account.service.api.dto.PerformTransactionRequest;
import com.example.camunda.demo.account.service.api.enumeration.TransactionType;
import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import com.example.camunda.demo.bpmn.engine.client.AccountClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SourceDebitDelegate extends JavaDelegateWrapper {

  private static final String NOT_ENOUGH_MONEY_ERROR_CODE = "NotEnoughMoney";
  private static final String NOT_ENOUGH_MONEY_ERROR_REASON =
      "На исходном счете недостаточно средств";

  private final AccountClient accountClient;

  @Override
  protected void execute(DelegateExecutionWrapper execution) throws Exception {
    log.info("Performing source debit transaction");

    var currencyExchangeRequest = execution.getVariable(Variables.CURRENCY_EXCHANGE_REQUEST);

    var request = new PerformTransactionRequest();
    request.setCurrency(currencyExchangeRequest.getSource().getCurrency());
    request.setAmount(currencyExchangeRequest.getSource().getAmount());
    request.setTransactionType(TransactionType.WITHDRAW);

    try {
      accountClient.performTransaction(
          currencyExchangeRequest.getUserId(),
          currencyExchangeRequest.getSource().getAccountId(),
          request
      );

      log.info("Source debit transaction completed");
    } catch (FeignException.Conflict ignored) {
      log.warn("Source debit transaction failed. Not enough money");
      execution.setVariable(Variables.ERROR_REASON, NOT_ENOUGH_MONEY_ERROR_REASON);
      throw new BpmnError(NOT_ENOUGH_MONEY_ERROR_CODE);
    }
  }

}
