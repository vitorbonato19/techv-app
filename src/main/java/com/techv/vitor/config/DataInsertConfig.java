package com.techv.vitor.config;

import com.techv.vitor.service.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInsertConfig implements CommandLineRunner {

    private final RoleService roleService;

    public DataInsertConfig(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
    }
}
