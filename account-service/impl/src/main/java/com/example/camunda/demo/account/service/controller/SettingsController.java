package com.example.camunda.demo.account.service.controller;

import com.example.camunda.demo.account.service.setting.TransactionsHandlingSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SettingsController {

  @RequestMapping(
      method = RequestMethod.PUT,
      value = "/api/v1/test/settings/transaction-handling"
  )
  ResponseEntity<Void> changeTransactionHandlingSetting(
      @RequestBody TransactionsHandlingSetting.Value value
  ) {
    log.info("New transaction handling setting: {}", value);
    TransactionsHandlingSetting.setValue(value);
    return ResponseEntity.noContent().build();
  }
}
