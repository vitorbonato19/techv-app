package com.techv.vitor.config;

import com.techv.vitor.service.RoleService;
import com.techv.vitor.service.SectorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInsertConfig implements CommandLineRunner {

    private final RoleService roleService;

    private final SectorService sectorService;

    public DataInsertConfig(RoleService roleService, SectorService sectorService) {
        this.roleService = roleService;
        this.sectorService = sectorService;
    }

    @Override
    public void run(String... args) throws Exception {
        roleService.initRoles();
        sectorService.insert();
    }
}
