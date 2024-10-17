package com.techv.vitor.application.usecases;

import com.techv.vitor.application.gateways.UserGateway;
import com.techv.vitor.domain.entity.User;

import java.util.List;

public class UserFindService {

    private final UserGateway userGateway;

    public UserFindService(UserGateway userGateway) {
        this.userGateway = userGateway;
    }
}
