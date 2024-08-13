package com.techv.vitor.config;


import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Admin;
import com.techv.vitor.entity.enums.Integrated;
import com.techv.vitor.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
@Profile("h2")
public class H2Config implements CommandLineRunner {

    private final UserRepository userRepository;

    public H2Config(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User(null, "admin", "admin@admin", "admin", Integrated.TRUE, LocalDateTime.now(), Admin.ADMIN);

        userRepository.save(admin);

    }

}
