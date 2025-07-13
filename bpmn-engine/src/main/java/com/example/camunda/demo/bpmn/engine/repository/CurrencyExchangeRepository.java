package com.example.camunda.demo.bpmn.engine.repository;

import com.example.camunda.demo.bpmn.engine.entity.CurrencyExchangeEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchangeEntity, UUID> {
  List<CurrencyExchangeEntity> findByOrderByCreatedAtDesc();
}
