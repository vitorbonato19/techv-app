package com.techv.vitor.controller.dto;

public class UserRequestDto {

    private String username;
    private String email;
    private String password;
    private String cep;

    public UserRequestDto() {

    }

    public UserRequestDto(String username, String email, String password, String cep) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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
