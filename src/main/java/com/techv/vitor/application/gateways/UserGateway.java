package com.techv.vitor.application.gateways;

import com.techv.vitor.domain.entity.Login;
import com.techv.vitor.domain.entity.User;

import java.util.List;

public interface UserGateway {

    User loginUser(Login login);

    List<User> findAll();

    User findById(Long id);

    User createUser(User user);
}
