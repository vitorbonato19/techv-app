package com.techv.vitor.repository;

import com.techv.vitor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
