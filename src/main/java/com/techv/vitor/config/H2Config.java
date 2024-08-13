package com.techv.vitor.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("h2")
public class H2Config extends CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
