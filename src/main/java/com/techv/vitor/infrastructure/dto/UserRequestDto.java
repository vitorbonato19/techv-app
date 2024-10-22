package com.techv.vitor.infrastructure.dto;

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


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder() {

        private String username;
        private String password;
        private String email;
        private String zipCode;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserRequestDto build() {
            return new UserRequestDto(username, email, password, zipCode);
        }
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
