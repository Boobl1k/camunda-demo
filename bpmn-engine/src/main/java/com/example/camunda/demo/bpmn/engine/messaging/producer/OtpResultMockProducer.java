package com.example.camunda.demo.bpmn.engine.messaging.producer;

import com.example.camunda.demo.bpmn.engine.dto.external.otp.OtpResultDto;
import io.github.springwolf.bindings.kafka.annotations.KafkaAsyncOperationBinding;
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.mapping.AbstractJavaTypeMapper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OtpResultMockProducer {
  private final KafkaTemplate<String, OtpResultDto> kafkaTemplate;

  @Setter(onMethod_ = @Value("${mock.otp.result-producer.topic}"))
  private String topic;

  @AsyncPublisher(
      operation = @AsyncOperation(
          channelName = "${mock.otp.result-producer.topic}",
          description = "Отправка результата OTP",
          headers = @AsyncOperation.Headers(
              values = {
                  @AsyncOperation.Headers.Header(
                      name = AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME,
                      value = "com.example.camunda.demo.dto.external.otp.OtpResultDto"
                  )
              }
          )
      )
  )
  @KafkaAsyncOperationBinding
  public void sendResponse(@Payload OtpResultDto result) {
    log.info("producing OtpResultDto: {}", result);
    kafkaTemplate.send(topic, result);
  }
}
