package com.techv.vitor.application.usecases;

import com.techv.vitor.application.gateways.UserGateway;
import com.techv.vitor.domain.entity.Login;
import com.techv.vitor.domain.entity.User;

public class UserLoginService {

    private final UserGateway userGateway;

    public UserLoginService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }
}
