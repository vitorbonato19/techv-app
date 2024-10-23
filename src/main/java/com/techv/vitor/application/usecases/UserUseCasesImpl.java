package com.techv.vitor.application.usecases;

import com.techv.vitor.application.entity.Login;
import com.techv.vitor.application.entity.User;
import com.techv.vitor.application.gateways.UserGateway;

import java.util.List;

public class UserUseCasesImpl implements UserUseCases {

    private final UserGateway userGateway;

    public UserUseCasesImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User loginUser(Login login) {
        return userGateway.loginUser(login);
    }

    @Override
    public List<User> findAll(int page, int size) {
        return userGateway.findAll(page, size);
    }

    @Override
    public User findById(Long id) {
        return userGateway.findById(id);
    }

    @Override
    public User createUser(User user) {
        return userGateway.createUser(user);
    }
}
