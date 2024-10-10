package com.techv.vitor.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.Cep;
import com.techv.vitor.entity.Sector;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserResponseDto {

    private Long id;

    private HttpStatus status;

    private HttpStatus statusCode;

    private String username;

    private String password;

    private String email;

    private com.techv.vitor.entity.Cep Cep;

    private Sector sector;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;

    public UserResponseDto() {

    }

    public UserResponseDto(Long id, String username, String password, String email, LocalDateTime lastModified, HttpStatus status, HttpStatus statusCode, com.techv.vitor.entity.Cep cep, Sector sector) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastModified = lastModified;
        this.status = status;
        this.statusCode = statusCode;
        Cep = cep;
        this.sector = sector;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public com.techv.vitor.entity.Cep getCep() {
        return Cep;
    }

    public void setCep(com.techv.vitor.entity.Cep cep) {
        Cep = cep;
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
