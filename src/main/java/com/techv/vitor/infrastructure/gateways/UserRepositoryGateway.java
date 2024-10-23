package com.techv.vitor.infrastructure.gateways;

import com.techv.vitor.application.gateways.UserGateway;
import com.techv.vitor.application.entity.Login;
import com.techv.vitor.application.entity.User;
import com.techv.vitor.infrastructure.dto.UserResponseDto;
import com.techv.vitor.infrastructure.entity.UserEntity;
import com.techv.vitor.infrastructure.exception.EntityNotFoundException;
import com.techv.vitor.infrastructure.mapper.UserEntityMapper;
import com.techv.vitor.infrastructure.persistence.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }


    @Override
    public User loginUser(Login login) {
        return userRepository
                .findByUsername(login.username())
                .map(userEntityMapper::entityToDomain)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "User not found, verifiy request",
                                HttpStatus.NOT_FOUND)
                );
    }

    @Override
    public List<User> findAll(int number, int size) {
        var entity = userRepository.findAll(PageRequest.of(number, size));
        return entity
                .getContent()
                .stream()
                .map(userEntityMapper::entityToDomain)
                .collect(Collectors.toList());
    }


    @Override
    public User findById(Long id) {
        return userRepository
                        .findById(id)
                        .map(userEntityMapper::entityToDomain)
                        .orElseThrow(
                                () -> new EntityNotFoundException(
                                        "User not found, verifiy request",
                                        HttpStatus.NOT_FOUND)
                        );
    }

    @Override
    public User createUser(User user) {
        var entity = userRepository.save(userEntityMapper.domainToEntity(user));
        return userEntityMapper.entityToDomain(entity);
    }
}
