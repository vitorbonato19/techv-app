package com.techv.vitor.main;


import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.Sector;
import com.techv.vitor.entity.User;
import com.techv.vitor.repository.RoleRepository;
import com.techv.vitor.repository.SectorRepository;
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

    private final SectorRepository sectorRepository;

    private final RoleRepository roleRepository;

    public AdminInsert(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SectorRepository sectorRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sectorRepository = sectorRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var role = roleRepository.findByName(Roles.Values.ADMIN.name());
        var sector = sectorRepository.findByName(Sector.Values.TI.name());
        Optional<User> userAdmin = userRepository.findByUsername("TI");

        userAdmin.ifPresentOrElse(
                user -> System.out.println("admin already exists!"),
                () -> {
                    User user = new User();
                    user.setUsername("TI");
                    user.setPassword("petcamp");
                    user.setRoles(Set.of(role));
                    user.setEmail("vitor.camargo@petcamp.com.br");
                    user.setSector(Set.of(sector));
                    user.setLastModified(LocalDateTime.now());
                    userRepository.save(user);
                }
        );
    }

}
