package com.techv.vitor.application.usecases;

import com.techv.vitor.application.gateways.UserGateway;
import com.techv.vitor.domain.entity.User;

public class UserCreateService {

    private final UserGateway userGateway;

    public UserCreateService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

}
