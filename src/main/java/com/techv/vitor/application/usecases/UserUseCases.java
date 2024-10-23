package com.techv.vitor.application.usecases;

import com.techv.vitor.application.entity.Login;
import com.techv.vitor.application.entity.User;

import java.util.List;

public interface UserUseCases {

    User loginUser(Login login);

    List<User> findAll(int page, int size);

    User findById(Long id);

    User createUser(User user);
}
