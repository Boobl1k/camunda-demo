package com.example.camunda.demo.account.service.api;

import com.example.camunda.demo.account.service.api.dto.UserDto;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserApi {

  @RequestMapping(
      value = "/api/v1/users",
      method = RequestMethod.GET
  )
  ResponseEntity<List<UserDto>> getUsers();

}
