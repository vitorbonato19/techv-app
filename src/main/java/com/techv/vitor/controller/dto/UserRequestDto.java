package com.techv.vitor.controller.dto;

import com.techv.vitor.entity.enums.Admin;
import com.techv.vitor.entity.enums.Integrated;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

public class UserRequestDto {

    private String username;
    private String email;
    private String password;
    private Integer integrated;
    private Integer admin;

    public UserRequestDto() {

    }

    public UserRequestDto(String username, String email, String password, Integer integrated, Integer admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.integrated = integrated;
        this.admin = admin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIntegrated() {
        return integrated;
    }

    public void setIntegrated(Integer integrated) {
        this.integrated = integrated;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
