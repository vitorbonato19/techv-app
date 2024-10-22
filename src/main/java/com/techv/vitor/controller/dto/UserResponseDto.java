package com.techv.vitor.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.Roles;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class UserResponseDto {

    private Long id;
    private String username;

    private String email;
    private String zipCode;

    private Set<Roles> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;

    public UserResponseDto() {

    }

    public UserResponseDto(Long id,
                           String username,
                           String email,
                           LocalDateTime lastModified,
                           String zipCode,
                           Set<Roles> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.zipCode = zipCode;
        this.lastModified = lastModified;
        this.roles = roles;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
