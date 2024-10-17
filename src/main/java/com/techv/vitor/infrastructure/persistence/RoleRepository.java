package com.techv.vitor.infrastructure.persistence;

import com.techv.vitor.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}
