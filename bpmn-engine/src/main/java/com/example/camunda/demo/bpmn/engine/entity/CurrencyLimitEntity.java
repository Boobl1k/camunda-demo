package com.example.camunda.demo.bpmn.engine.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "currency_limit")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyLimitEntity {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "currency", nullable = false)
  private String currency;

  @Column(name = "limit", nullable = false)
  private Long limit;
}
