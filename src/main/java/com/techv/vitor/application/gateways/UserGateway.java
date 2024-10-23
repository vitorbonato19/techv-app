package com.techv.vitor.application.gateways;

import com.techv.vitor.application.entity.Login;
import com.techv.vitor.application.entity.User;

import java.util.List;

public interface UserGateway {

    User loginUser(Login login);

    List<User> findAll(int number, int size);

    User findById(Long id);

    User createUser(User user);
}
