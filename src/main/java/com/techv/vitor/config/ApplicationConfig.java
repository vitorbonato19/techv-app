package com.techv.vitor.config;

import com.techv.vitor.entity.Data;
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
