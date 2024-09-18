package com.techv.vitor.config;

import com.techv.vitor.entity.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class ApplicationConfig {

    @Bean
    public Data data() {
        var data = new Data();
        return data;
    }

    @Bean
    public HttpHeaders headers() {
        var headers = new HttpHeaders();
        return headers;
    }
}
