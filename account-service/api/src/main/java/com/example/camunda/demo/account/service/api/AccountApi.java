package com.example.camunda.demo.account.service.api;

import com.example.camunda.demo.account.service.api.dto.PerformTransactionRequest;
import com.example.camunda.demo.account.service.api.dto.PerformTransactionResponse;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface AccountApi {

  @RequestMapping(
      value = "/api/v1/users/{userId}/accounts/{accountId}/perform-transaction",
      method = RequestMethod.POST
  )
  ResponseEntity<PerformTransactionResponse> performTransaction(
      @PathVariable("userId") UUID userId,
      @PathVariable("accountId") UUID accountId,
      @RequestBody PerformTransactionRequest request
  );

}
