package com.example.camunda.demo.bpmn.engine.repository;

import com.example.camunda.demo.bpmn.engine.entity.OtpEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<OtpEntity, UUID> {
  Optional<OtpEntity> findByUserId(UUID userId);
}
