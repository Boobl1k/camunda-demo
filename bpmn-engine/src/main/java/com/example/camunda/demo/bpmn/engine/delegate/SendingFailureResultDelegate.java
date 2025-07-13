package com.example.camunda.demo.bpmn.engine.delegate;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import com.example.camunda.demo.bpmn.engine.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendingFailureResultDelegate extends JavaDelegateWrapper {
  private final CurrencyExchangeService currencyExchangeService;

  @Override
  protected void execute(DelegateExecutionWrapper execution) {
    var currencyExchangeId = execution.getVariable(Variables.CURRENCY_EXCHANGE_ID);
    var errorReason = execution.getVariable(Variables.ERROR_REASON);

    currencyExchangeService.updateFailed(currencyExchangeId, errorReason);

    log.warn(
        "Currency exchange with id {} failed to complete. Error reason: {}",
        currencyExchangeId,
        errorReason
    );
  }
}
