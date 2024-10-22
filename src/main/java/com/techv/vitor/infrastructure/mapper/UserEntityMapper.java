package com.techv.vitor.infrastructure.mapper;

import com.techv.vitor.domain.entity.User;
import com.techv.vitor.infrastructure.dto.UserRequestDto;
import com.techv.vitor.infrastructure.dto.UserResponseDto;
import com.techv.vitor.infrastructure.entity.UserEntity;

public class UserEntityMapper {

    public User toDomain(UserEntity entity) {


    }

    public UserEntity domainToEntity(User domainUser) {

        return UserEntity
                .builder()
                .username(domainUser.username())
                .email(domainUser.email())
                .password(domainUser.password())
                .tickets(domainUser.tickets())
                .role(domainUser.role())
                .zipCode(domainUser.zipcode())
                .build();
    }

    public UserResponseDto toResponseDto(UserEntity userEntity) {

        return UserResponseDto
                .builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .lastModified(userEntity.getLastModified())
                .ticket(userEntity.getTickets())
                .roles(userEntity.getRoles())
                .zipCode(userEntity.getZipCode())
                .build();
    }

    public UserEntity requestToEntity(UserRequestDto requestDto) {

        return UserEntity
                .builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .zipCode(requestDto.getZipCode())
                .build();
    }
}
