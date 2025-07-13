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
public class TargetCreditDelegate extends JavaDelegateWrapper {

  private static final String FAILED_TRANSACTION_ERROR_CODE = "ImpossibleToProceedTransaction";
  private static final String FAILED_TRANSACTION_ERROR_REASON = "Невозможно выполнить транзакцию";

  private final AccountClient accountClient;

  @Override
  protected void execute(DelegateExecutionWrapper execution) throws Exception {
    log.info("Performing target credit transaction");

    execution.setVariable(Variables.TRANSACTION_COMPLETED, false);

    var currencyExchangeRequest = execution.getVariable(Variables.CURRENCY_EXCHANGE_REQUEST);
    var transactionCalculationResponse =
        execution.getVariable(Variables.TRANSACTION_CALCULATION_RESPONSE);

    var request = new PerformTransactionRequest();
    request.setCurrency(transactionCalculationResponse.getTarget().getCurrency());
    request.setAmount(transactionCalculationResponse.getTarget().getAmount());
    request.setTransactionType(TransactionType.DEPOSIT);

    try {
      accountClient.performTransaction(
          currencyExchangeRequest.getUserId(),
          currencyExchangeRequest.getTarget().getAccountId(),
          request
      );
      log.info("Target credit transaction completed");
    } catch (FeignException.BadRequest ignored) {
      log.warn("Target credit transaction failed");
      execution.setVariable(Variables.ERROR_REASON, FAILED_TRANSACTION_ERROR_REASON);
      throw new BpmnError(FAILED_TRANSACTION_ERROR_CODE);
    }

    execution.setVariable(Variables.TRANSACTION_COMPLETED, true);
  }

}
