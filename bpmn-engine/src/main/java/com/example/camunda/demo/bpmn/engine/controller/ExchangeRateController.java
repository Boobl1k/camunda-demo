package com.example.camunda.demo.bpmn.engine.controller;

import com.example.camunda.demo.bpmn.engine.dto.external.ExchangeRateDto;
import com.example.camunda.demo.bpmn.engine.service.mock.ExchangeRateMockService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController {

  private final ExchangeRateMockService exchangeRateMockService;

  @RequestMapping(
      value = "/api/v1/exchange-rates",
      method = RequestMethod.GET
  )
  public ResponseEntity<List<ExchangeRateDto>> getExchangeRates() {
    return ResponseEntity.ok(exchangeRateMockService.getAllExchangeRates());
  }

}
