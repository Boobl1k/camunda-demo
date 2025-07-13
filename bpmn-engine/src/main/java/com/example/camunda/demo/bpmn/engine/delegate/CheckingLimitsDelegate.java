package com.example.camunda.demo.bpmn.engine.delegate;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import com.example.camunda.demo.bpmn.engine.service.mock.CurrencyLimitMockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckingLimitsDelegate extends JavaDelegateWrapper {

  private static final String TARGET_AMOUNT_EXCEEDS_LIMIT_ERROR_CODE = "targetAmountExceedsLimit";
  private static final String TARGET_AMOUNT_EXCEEDS_LIMIT_ERROR_REASON_TEMPLATE =
      "Указанная сумма %s превосходит лимит суммы транзакции для валюты %s (%s)";

  private final CurrencyLimitMockService currencyLimitMockService;

  @Override
  protected void execute(DelegateExecutionWrapper execution) {
    log.info("Checking limits");
    var transactionCalculationResponse =
        execution.getVariable(Variables.TRANSACTION_CALCULATION_RESPONSE);
    var limit = currencyLimitMockService.getCurrencyLimitByCurrency(
        transactionCalculationResponse.getTarget().getCurrency()
    );
    if (transactionCalculationResponse.getTarget().getAmount() > limit.getLimit()) {
      log.info("Limit check failed");
      execution.setVariable(
          Variables.ERROR_REASON,
          String.format(
              TARGET_AMOUNT_EXCEEDS_LIMIT_ERROR_REASON_TEMPLATE,
              transactionCalculationResponse.getTarget().getAmount(),
              limit.getCurrency(),
              limit.getLimit()
          )
      );
      throw new BpmnError(TARGET_AMOUNT_EXCEEDS_LIMIT_ERROR_CODE);
    }
    log.info("Limit check succeed");
  }
}
