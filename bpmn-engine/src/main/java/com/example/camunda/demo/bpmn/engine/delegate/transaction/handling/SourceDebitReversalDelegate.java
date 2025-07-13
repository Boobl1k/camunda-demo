package com.example.camunda.demo.bpmn.engine.delegate.transaction.handling;

import com.example.camunda.demo.account.service.api.dto.PerformTransactionRequest;
import com.example.camunda.demo.account.service.api.enumeration.TransactionType;
import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import com.example.camunda.demo.bpmn.engine.client.AccountClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SourceDebitReversalDelegate extends JavaDelegateWrapper {

  private final AccountClient accountClient;

  @Override
  protected void execute(DelegateExecutionWrapper execution) throws Exception {
    log.info("Performing source debit reversal transaction");

    var currencyExchangeRequest = execution.getVariable(Variables.CURRENCY_EXCHANGE_REQUEST);

    var request = new PerformTransactionRequest();
    request.setCurrency(currencyExchangeRequest.getSource().getCurrency());
    request.setAmount(currencyExchangeRequest.getSource().getAmount());
    request.setTransactionType(TransactionType.DEPOSIT);

    accountClient.performTransaction(
        currencyExchangeRequest.getUserId(),
        currencyExchangeRequest.getSource().getAccountId(),
        request
    );

    log.info("Source debit reversal transaction completed");
  }
}
