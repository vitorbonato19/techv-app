package com.techv.vitor.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.infrastructure.entity.RoleEntity;
import com.techv.vitor.infrastructure.entity.TicketEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserResponseDto {

    private Long id;
    private String username;
    private String email;
    private String zipCode;
    private List<TicketEntity> tickets;
    private Set<RoleEntity> roles;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;

    public UserResponseDto() {

    }

    public UserResponseDto(Long id,
                           String username,
                           String email,
                           LocalDateTime lastModified,
                           String zipCode,
                           Set<RoleEntity> roles,
                           List<TicketEntity> tickets) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.zipCode = zipCode;
        this.lastModified = lastModified;
        this.roles = roles;
        this.tickets = tickets;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private String username;
        private String email;
        private String zipCode;
        private LocalDateTime lastModified;
        private Set<RoleEntity> roles;
        private List<TicketEntity> tickets;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder lastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder ticket(List<TicketEntity> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder roles(Set<RoleEntity> roles) {
            this.roles = roles;
            return this;
        }

        public UserResponseDto build() {
            return new UserResponseDto(id, username, email, lastModified, zipCode, roles, tickets);
        }

    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
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
