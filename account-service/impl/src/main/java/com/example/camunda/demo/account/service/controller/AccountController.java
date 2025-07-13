package com.example.camunda.demo.account.service.controller;

import com.example.camunda.demo.account.service.api.AccountApi;
import com.example.camunda.demo.account.service.api.dto.PerformTransactionRequest;
import com.example.camunda.demo.account.service.api.dto.PerformTransactionResponse;
import com.example.camunda.demo.account.service.exception.EntityNotFoundException;
import com.example.camunda.demo.account.service.service.AccountService;
import com.example.camunda.demo.account.service.service.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

  private final AccountService accountService;
  private final UserService userService;

  @Override
  public ResponseEntity<PerformTransactionResponse> performTransaction(
      UUID userId,
      UUID accountId,
      PerformTransactionRequest request
  ) {
    var user = userService.findUser(userId);
    if (user.isEmpty()) {
      throw new EntityNotFoundException("user", userId);
    }
    return ResponseEntity.ok(accountService.performTransaction(accountId, request));
  }

}
