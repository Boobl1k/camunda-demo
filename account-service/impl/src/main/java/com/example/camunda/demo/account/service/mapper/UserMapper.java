package com.example.camunda.demo.account.service.mapper;

import com.example.camunda.demo.account.service.api.dto.UserDto;
import com.example.camunda.demo.account.service.entity.UserEntity;
import com.example.camunda.demo.account.service.mapper.config.MapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
  UserDto map(UserEntity userEntity);
}
