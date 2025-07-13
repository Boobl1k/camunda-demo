package com.example.camunda.demo.account.service.repository;

import com.example.camunda.demo.account.service.entity.UserEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  @EntityGraph(attributePaths = "accounts")
  @Query("SELECT u FROM UserEntity u")
  List<UserEntity> findAllWithAccounts();
}
