package com.example.camunda.demo.bpmn.engine.messaging.consumer;

import com.example.camunda.demo.bpmn.engine.camunda.holder.Messages;
import com.example.camunda.demo.bpmn.engine.camunda.holder.Variables;
import com.example.camunda.demo.bpmn.engine.configuration.KafkaConfiguration;
import com.example.camunda.demo.bpmn.engine.dto.external.otp.OtpResultDto;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncListener;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtpResultListener {

  private final RuntimeService runtimeService;

  @AsyncListener(
      operation = @AsyncOperation(
          channelName = "${app.otp.result-consumer.topic}",
          description = "Результат OTP"
      )
  )
  @KafkaAsyncOperationBinding
  @KafkaListener(
      containerFactory = KafkaConfiguration.KAFKA_FACTORY,
      topics = "${app.otp.result-consumer.topic}",
      groupId = "${app.otp.result-consumer.group-id}"
  )
  public void consumeTransactionCompletionRequest(OtpResultDto otpResultDto) {
    log.info("consume OTP result: {}", otpResultDto);

    runtimeService.correlateMessage(
        Messages.OTP_RESULT,
        Collections.singletonMap(Variables.OTP_ID.getName(), otpResultDto.getOtpId().toString()),
        Collections.singletonMap(Variables.OTP_RESULT.getName(), otpResultDto)
    );
  }
}
