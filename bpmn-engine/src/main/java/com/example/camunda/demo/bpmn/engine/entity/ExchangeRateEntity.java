package com.example.camunda.demo.bpmn.engine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "exchange_rate")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExchangeRateEntity {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "source_currency", nullable = false)
  private String sourceCurrency;
  @Column(name = "target_currency", nullable = false)
  private String targetCurrency;
  @Column(name = "exchange_rate", nullable = false)
  private BigDecimal exchangeRate;
}
