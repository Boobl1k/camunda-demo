package com.example.camunda.demo.bpmn.engine.entity;

import com.example.camunda.demo.bpmn.engine.dto.CurrencyExchangeRequest;
import com.example.camunda.demo.bpmn.engine.dto.external.transaction.calculation.TransactionCalculationResponse;
import com.example.camunda.demo.bpmn.engine.enumeration.CurrencyExchangeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "currency_exchange")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyExchangeEntity {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "status", nullable = false)
  private CurrencyExchangeStatus status;

  @Column(name = "error_reason", nullable = true)
  private String errorReason;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "request", nullable = false)
  private CurrencyExchangeRequest request;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "transaction_calculation_response", nullable = true)
  private TransactionCalculationResponse transactionCalculationResponse;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
}
