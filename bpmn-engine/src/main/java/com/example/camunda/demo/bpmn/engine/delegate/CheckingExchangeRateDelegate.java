package com.example.camunda.demo.bpmn.engine.delegate;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import com.example.camunda.demo.bpmn.engine.service.mock.ExchangeRateMockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckingExchangeRateDelegate extends JavaDelegateWrapper {

  private static final String DIFFERENT_EXCHANGE_RATE_ERROR_CODE = "DifferentExchangeRate";
  private static final String DIFFERENT_EXCHANGE_RATE_ERROR_REASON_TEMPLATE =
      "Курс валют для пары %s:%s (%s) отличается от курса в запросе: %s";
  private static final String EXCHANGE_RATE_NOT_FOUND_ERROR_CODE = "ExchangeRateNotFound";
  private static final String EXCHANGE_RATE_NOT_FOUND_ERROR_REASON_TEMPLATE =
      "Невозможно обменять %s на %s. Курса для такой пары не существует";

  private final ExchangeRateMockService exchangeRateMockService;

  @Override
  protected void execute(DelegateExecutionWrapper execution) {
    log.info("Checking exchange rate");

    var currencyExchangeRequest = execution.getVariable(Variables.CURRENCY_EXCHANGE_REQUEST);
    var rate = exchangeRateMockService.getExchangeRateBySourceAndTarget(
        currencyExchangeRequest.getSource().getCurrency(),
        currencyExchangeRequest.getTarget().getCurrency()
    ).orElseGet(() -> {
      log.warn("Exchange rate for pair not found");
      execution.setVariable(
          Variables.ERROR_REASON,
          String.format(
              EXCHANGE_RATE_NOT_FOUND_ERROR_REASON_TEMPLATE,
              currencyExchangeRequest.getSource().getCurrency(),
              currencyExchangeRequest.getTarget().getCurrency()
          )
      );
      throw new BpmnError(EXCHANGE_RATE_NOT_FOUND_ERROR_CODE);
    });

    if (!rate.getExchangeRate().equals(currencyExchangeRequest.getExchangeRate())) {
      log.warn("Exchange rate in request is wrong");
      execution.setVariable(
          Variables.ERROR_REASON,
          String.format(
              DIFFERENT_EXCHANGE_RATE_ERROR_REASON_TEMPLATE,
              currencyExchangeRequest.getSource().getCurrency(),
              currencyExchangeRequest.getTarget().getCurrency(),
              rate.getExchangeRate(),
              currencyExchangeRequest.getExchangeRate()
          )
      );
      throw new BpmnError(DIFFERENT_EXCHANGE_RATE_ERROR_CODE);
    }

    log.info("Checking exchange rate completed");
  }
}
