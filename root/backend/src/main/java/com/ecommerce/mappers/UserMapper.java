package com.ecommerce.mappers;

import com.ecommerce.dtos.SignUpDto;
import com.ecommerce.dtos.UserDto;
import com.ecommerce.entites.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
