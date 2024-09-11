package com.techv.vitor.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserResponseDto {

    private Long id;

    private HttpStatus status;

    private HttpStatus statusCode;

    private String username;

    private String password;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;

    public UserResponseDto() {

    }

    public UserResponseDto(Long id, String username, String password, String email, LocalDateTime lastModified, HttpStatus status, HttpStatus statusCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastModified = lastModified;
        this.status = status;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode.value();
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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

}
