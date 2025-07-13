package com.example.camunda.demo.bpmn.engine.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.ContainerCustomizer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class KafkaConfiguration {

  public static final String KAFKA_FACTORY = "kafkaListenerContainerFactory";

  private final KafkaProperties kafkaProperties;
  private final ObjectMapper objectMapper;

  @Bean(name = KAFKA_FACTORY)
  public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
      ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
      ObjectProvider<ContainerCustomizer<Object, Object,
          ConcurrentMessageListenerContainer<Object, Object>>> kafkaContainerCustomizer,
      ObjectProvider<SslBundles> sslBundles,
      JsonMessageConverter jsonMessageConverter
  ) {
    var factory = new ConcurrentKafkaListenerContainerFactory<>();
    var configs = kafkaProperties.buildConsumerProperties(sslBundles.getIfAvailable());
    var consumerFactory = new DefaultKafkaConsumerFactory<>(configs);

    factory.setRecordMessageConverter(jsonMessageConverter);
    factory.setBatchMessageConverter(new BatchMessagingMessageConverter(jsonMessageConverter));

    configurer.configure(factory, consumerFactory);
    kafkaContainerCustomizer.ifAvailable(factory::setContainerCustomizer);

    return factory;
  }

  @Bean
  public JsonMessageConverter jsonMessageConverter() {
    return new ByteArrayJsonMessageConverter(objectMapper);
  }

  @Bean
  public <K, V> KafkaTemplate<K, V> kafkaTemplate() {
    var configuration = new HashMap<>(kafkaProperties.buildProducerProperties(null));
    var producerFactory = new DefaultKafkaProducerFactory<K, V>(configuration);

    return new KafkaTemplate<>(producerFactory);
  }
}
