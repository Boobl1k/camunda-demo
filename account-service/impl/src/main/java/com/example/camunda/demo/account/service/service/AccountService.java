package com.example.camunda.demo.account.service.service;

import com.example.camunda.demo.account.service.api.dto.PerformTransactionRequest;
import com.example.camunda.demo.account.service.api.dto.PerformTransactionResponse;
import com.example.camunda.demo.account.service.exception.CannotPerformTransactionException;
import com.example.camunda.demo.account.service.exception.EntityNotFoundException;
import com.example.camunda.demo.account.service.exception.InsufficientFundsException;
import com.example.camunda.demo.account.service.mapper.TransactionMapper;
import com.example.camunda.demo.account.service.repository.AccountRepository;
import com.example.camunda.demo.account.service.setting.TransactionsHandlingSetting;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;
  private final TransactionMapper transactionMapper;

  @Transactional
  public PerformTransactionResponse performTransaction(
      UUID accountId,
      PerformTransactionRequest performTransactionRequest
  ) {
    log.info("Performing transaction for account {}, type: {}, amount: {}",
        accountId,
        performTransactionRequest.getTransactionType(),
        performTransactionRequest.getAmount()
    );

    var account = accountRepository.findByIdForUpdate(accountId)
        .orElseThrow(() -> new EntityNotFoundException("account", accountId));

    long newAmount = switch (performTransactionRequest.getTransactionType()) {
      case DEPOSIT -> {
        handleFailureCase();
        yield account.getAmount() + performTransactionRequest.getAmount();
      }
      case WITHDRAW -> account.getAmount() - performTransactionRequest.getAmount();
    };

    if (newAmount < 0) {
      throw new InsufficientFundsException();
    }

    account.setAmount(newAmount);
    accountRepository.save(account);

    log.info("Transaction completed for account {}", accountId);

    return transactionMapper.map(performTransactionRequest, account);
  }

  private void handleFailureCase() {
    if (TransactionsHandlingSetting.getValue() ==
        TransactionsHandlingSetting.Value.HANDLE_NEXT_DEPOSIT_WITH_FAILURE) {
      log.warn("Handling transaction with failure");
      TransactionsHandlingSetting.reset();
      throw new CannotPerformTransactionException();
    }
  }

}
