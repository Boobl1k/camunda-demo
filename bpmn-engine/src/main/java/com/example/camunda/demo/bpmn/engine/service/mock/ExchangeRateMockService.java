package com.example.camunda.demo.bpmn.engine.service.mock;

import com.example.camunda.demo.bpmn.engine.dto.external.ExchangeRateDto;
import com.example.camunda.demo.bpmn.engine.mapper.ExchangeRateMapper;
import com.example.camunda.demo.bpmn.engine.repository.ExchangeRateRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateMockService {
  private final ExchangeRateRepository exchangeRateRepository;
  private final ExchangeRateMapper exchangeRateMapper;

  public Optional<ExchangeRateDto> getExchangeRateBySourceAndTarget(String source, String target) {
    return exchangeRateRepository.getBySourceCurrencyAndTargetCurrency(source, target)
        .map(exchangeRateMapper::map);
  }

  public List<ExchangeRateDto> getAllExchangeRates() {
    return exchangeRateRepository.findAll().stream().map(exchangeRateMapper::map).toList();
  }

}
