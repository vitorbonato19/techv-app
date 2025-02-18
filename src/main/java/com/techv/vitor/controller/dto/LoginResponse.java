package com.techv.vitor.controller.dto;

import java.time.Instant;

public class LoginResponse {

    private String accessToken;
    private Long expiresIn;
    private Instant createdAt;

    public LoginResponse() {

    }

    public LoginResponse(String accessToken, Long expiresIn, Instant createdAt) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.createdAt = createdAt;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
