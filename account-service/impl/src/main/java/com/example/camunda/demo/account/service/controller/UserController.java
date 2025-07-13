package com.example.camunda.demo.account.service.controller;

import com.example.camunda.demo.account.service.api.UserApi;
import com.example.camunda.demo.account.service.api.dto.UserDto;
import com.example.camunda.demo.account.service.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<List<UserDto>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

}
