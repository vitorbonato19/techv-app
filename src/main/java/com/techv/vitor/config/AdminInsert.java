package com.techv.vitor.config;


import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.User;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminInsert implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    public AdminInsert(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var role = roleRepository.findByName(Roles.Values.ADMIN.name());
        Optional<User> userAdmin = userRepository.findByUsername("TI");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin already exists!"),
                () -> {
                    User user = new User();
                    user.setUsername("TI");
                    user.setPassword("petcamp");
                    user.setRoles(Set.of(role));
                    user.setEmail("vitor.camargo@petcamp.com.br");
                    user.setLastModified(LocalDateTime.now());
                    userRepository.save(user);
                }
        );
    }

}
