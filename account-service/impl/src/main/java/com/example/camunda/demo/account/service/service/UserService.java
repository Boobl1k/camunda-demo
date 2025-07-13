package com.example.camunda.demo.account.service.service;

import com.example.camunda.demo.account.service.api.dto.UserDto;
import com.example.camunda.demo.account.service.mapper.UserMapper;
import com.example.camunda.demo.account.service.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public Optional<UserDto> findUser(UUID userId) {
    return userRepository.findById(userId).map(userMapper::map);
  }

  public List<UserDto> getUsers() {
    return userRepository.findAllWithAccounts()
        .stream()
        .map(userMapper::map)
        .toList();
  }

}
