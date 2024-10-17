package com.techv.vitor.infrastructure.gateways;

import com.techv.vitor.application.gateways.UserGateway;
import com.techv.vitor.domain.entity.Login;
import com.techv.vitor.domain.entity.User;
import com.techv.vitor.infrastructure.persistence.UserRepository;

import java.util.List;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserRepositoryGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loginUser(Login login) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User createUser(User user) {
        return null;
    }
}
