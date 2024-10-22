package com.techv.vitor.infrastructure.gateways;

import com.techv.vitor.application.gateways.UserGateway;
import com.techv.vitor.domain.entity.Login;
import com.techv.vitor.domain.entity.User;
import com.techv.vitor.infrastructure.dto.UserResponseDto;
import com.techv.vitor.infrastructure.mapper.UserEntityMapper;
import com.techv.vitor.infrastructure.persistence.UserRepository;

import java.util.List;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserResponseDto loginUser(Login login) {
        return null;
    }

    @Override
    public List<UserResponseDto> findAll() {
        return null;
    }

    @Override
    public UserResponseDto findById(Long id) {
        return null;
    }
    @Override
    public User createUser(User domainObj) {
        var entity = userEntityMapper.domainToEntity(domainObj);
        var toDomain = userRepository.save(entity);
        var response = userEntityMapper.
        return userEntityMapper.toResponseDto(response);
    }
}
