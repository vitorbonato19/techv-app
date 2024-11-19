package com.techv.vitor.repository;

import com.techv.vitor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

    @Query(value = "select r.name from centraltechv.usersroles u " +
            "left join centraltechv.roles r on r.role_id = u.role_id " +
            "where user_id = :id", nativeQuery = true)
    public String findAdmin(Long id);
}
