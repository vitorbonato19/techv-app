package com.techv.vitor.controller.dto;

public class UserRequestDto {

    private String username;
    private String email;
    private String password;
    private String zipCode;

    public UserRequestDto() {

    }

    public UserRequestDto(String username, String email, String password, String zipCode) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

}
