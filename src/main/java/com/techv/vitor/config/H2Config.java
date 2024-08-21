package com.techv.vitor.config;


import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Admin;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@Profile("h2")
public class H2Config implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    public H2Config(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin already exists!"),
                () -> {
                    User user = new User();
                    user.setUsername("admin");
                    user.setPassword(bCryptPasswordEncoder.encode("admin"));
                    user.setAdmin(Admin.ADMIN);
                    user.setEmail("admin@admin.com");
                    userRepository.save(user);
                }
        );
    }

}
