package com.techv.vitor.repository;

import com.techv.vitor.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    @Query(nativeQuery = true, value = "select if (name is not null, 1,0) as bool from centraltechv.roles where name=:name;")
    boolean findByName(String name);

    @Query(nativeQuery = true, value = "select count(*) from centraltechv.roles")
    long countRoles();

    @Query(nativeQuery = true, value = "select id from centraltechv.roles where name=:name")
    long findId(String name);



}
