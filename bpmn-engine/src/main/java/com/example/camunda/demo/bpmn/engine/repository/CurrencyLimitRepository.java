package com.example.camunda.demo.bpmn.engine.repository;

import com.example.camunda.demo.bpmn.engine.entity.CurrencyLimitEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyLimitRepository extends JpaRepository<CurrencyLimitEntity, UUID> {
  CurrencyLimitEntity getByCurrency(String currency);
}
