package com.example.camunda.demo.bpmn.engine.repository;

import com.example.camunda.demo.bpmn.engine.entity.ExchangeRateEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, UUID> {
  Optional<ExchangeRateEntity> getBySourceCurrencyAndTargetCurrency(
      String sourceCurrency,
      String targetCurrency
  );
}
