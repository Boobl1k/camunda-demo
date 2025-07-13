package com.example.camunda.demo.account.service.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String entityName, UUID id) {
    super("Entity " + entityName + " with id " + id + " not found");
  }

}
