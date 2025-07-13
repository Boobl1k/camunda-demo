package com.example.camunda.demo.bpmn.engine.delegate;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.DelegateExecutionWrapper;
import com.example.camunda.demo.bpmn.engine.camunda.wrapper.JavaDelegateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HandlingOtpFailureDelegate extends JavaDelegateWrapper {

  private static final String WRONG_OTP_ERROR_REASON = "Был введен неверный одноразовый пароль";
  private static final String OTP_NOT_SOLVED_WITHIN_LIMIT =
      "OTP не был пройден за отведенное время";

  @Override
  protected void execute(DelegateExecutionWrapper execution) throws Exception {
    log.info("OTP failed");
    var otpResult = execution.getVariable(Variables.OTP_RESULT);

    var errorReason = otpResult != null && !otpResult.getSolved()
        ? WRONG_OTP_ERROR_REASON
        : OTP_NOT_SOLVED_WITHIN_LIMIT;

    execution.setVariable(Variables.ERROR_REASON, errorReason);
  }
}
