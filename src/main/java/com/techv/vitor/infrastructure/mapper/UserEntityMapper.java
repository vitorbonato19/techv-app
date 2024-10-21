package com.techv.vitor.infrastructure.mapper;

import com.techv.vitor.domain.entity.User;
import com.techv.vitor.infrastructure.entity.UserEntity;

public class UserEntityMapper {

    public UserEntity toEntity(User domainUser) {

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


}
