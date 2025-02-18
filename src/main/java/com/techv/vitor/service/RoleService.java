package com.techv.vitor.service;

import com.techv.vitor.entity.Roles;
import com.techv.vitor.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.countRoles() == 0) {
                roleRepository.save(new Roles(null, "ADMIN"));
                roleRepository.save(new Roles(null, "NOT_ADMIN"));
            }
        }

}
