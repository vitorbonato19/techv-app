package com.techv.vitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry cors) {

        cors.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:5500", "http://localhost:3000", "http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("Content-Type")
                .allowCredentials(true);
    }
}
