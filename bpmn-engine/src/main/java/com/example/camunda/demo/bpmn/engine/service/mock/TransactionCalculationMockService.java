package com.example.camunda.demo.bpmn.engine.service.mock;

import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationResponse;
import com.example.camunda.demo.bpmn.engine.mapper.TransactionCalculationMapper;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionCalculationMockService {

  private final TransactionCalculationMapper transactionCalculationMapper;

  public TransactionCalculationResponse calculateTransaction(
      TransactionCalculationRequest request
  ) {
    var targetAmountDecimal =
        request.getExchangeRate().multiply(BigDecimal.valueOf(request.getSource().getAmount()));
    var targetAmount = targetAmountDecimal.longValue();
    return transactionCalculationMapper.map(request, targetAmount);
  }

}
