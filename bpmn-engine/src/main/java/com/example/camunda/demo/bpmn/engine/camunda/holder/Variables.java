package com.example.camunda.demo.bpmn.engine.camunda.holder;

import com.example.camunda.demo.bpmn.engine.camunda.wrapper.ExecutionVariable;
import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.OtpResultDto;
import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationResponse;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Variables {

  public static final ExecutionVariable<UUID> CURRENCY_EXCHANGE_ID = () -> "currencyExchangeId";
  public static final ExecutionVariable<CurrencyExchangeRequest> CURRENCY_EXCHANGE_REQUEST =
      () -> "currencyExchangeRequest";
  public static final ExecutionVariable<TransactionCalculationResponse>
      TRANSACTION_CALCULATION_RESPONSE = () -> "transactionCalculationResponse";
  public static final ExecutionVariable<Boolean> TRANSACTION_COMPLETED =
      () -> "transactionCompleted";
  public static final ExecutionVariable<String> ERROR_REASON = () -> "errorReason";
  public static final ExecutionVariable<String> OTP_ID = () -> "otpId";
  public static final ExecutionVariable<OtpResultDto> OTP_RESULT = () -> "otpResult";

}
