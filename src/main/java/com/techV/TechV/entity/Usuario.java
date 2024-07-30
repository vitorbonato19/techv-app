package com.techV.TechV.entity;

import com.techV.TechV.entity.enums.Admin;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String username;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    private Admin admin;

    @OneToMany
    private List<Ticket> tickets;

    public Usuario() {

    }

    public Usuario(Long id, String username, String email, String password, Admin admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
