package com.example.camunda.demo.bpmn.engine.service.mock;

import com.example.camunda.demo.bpmn.engine.entity.CurrencyLimitEntity;
import com.example.camunda.demo.bpmn.engine.repository.CurrencyLimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyLimitMockService {
  private final CurrencyLimitRepository currencyLimitRepository;

  public CurrencyLimitEntity getCurrencyLimitByCurrency(String currency) {
    var result = currencyLimitRepository.getByCurrency(currency);
    if(result == null) {
      throw new NullPointerException("Currency limit for currency " + currency + " not found");
    }
    return result;
  }
}
