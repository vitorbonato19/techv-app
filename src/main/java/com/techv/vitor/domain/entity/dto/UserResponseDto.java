package com.techv.vitor.domain.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.Roles;
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
