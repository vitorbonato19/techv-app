package com.techv.vitor.controller.dto;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class UserResponseDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Boolean integrated;

    private LocalDateTime lastModified;

    public UserResponseDto() {

    }

    public UserResponseDto(Long id, String username, String password, String email, Boolean integrated, LocalDateTime lastModified) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.integrated = integrated;
        this.lastModified = lastModified;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIntegrated() {
        return integrated;
    }

    public void setIntegrated(Boolean integrated) {
        this.integrated = integrated;
    }
}
