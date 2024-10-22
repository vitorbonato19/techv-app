package com.techv.vitor.main;

import com.techv.vitor.infrastructure.entity.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class ApplicationConfig {

    @Bean
    public Data data() {
        return new Data();
    }

    @Bean
    public HttpHeaders headers() {
        return new HttpHeaders();
    }
}
