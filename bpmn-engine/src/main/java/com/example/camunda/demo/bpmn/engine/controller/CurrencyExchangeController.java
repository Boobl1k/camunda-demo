package com.example.camunda.demo.bpmn.engine.controller;

import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeDto;
import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeRequest;
import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeStartResponse;
import com.example.camunda.demo.bpmn.engine.service.CurrencyExchangeService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

  private final CurrencyExchangeService currencyExchangeService;

  @RequestMapping(
      method = RequestMethod.POST,
      value = "/api/v1/currency-exchange"
  )
  ResponseEntity<CurrencyExchangeStartResponse> processCurrencyExchangeRequest(
      @RequestBody CurrencyExchangeRequest request
  ) {
    return ResponseEntity.ok(currencyExchangeService.handleCurrencyExchangeRequest(request));
  }

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/api/v1/currency-exchange/{id}"
  )
  ResponseEntity<CurrencyExchangeDto> getCurrencyExchange(@PathVariable("id") UUID id) {
    return ResponseEntity.ok(currencyExchangeService.getCurrencyExchange(id));
  }

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/api/v1/currency-exchange"
  )
  ResponseEntity<List<CurrencyExchangeDto>> getCurrencyExchanges() {
    return ResponseEntity.ok(currencyExchangeService.getAllCurrencyExchanges());
  }
}
