package com.techv.vitor.entity;

import com.techv.vitor.entity.enums.Admin;
import com.techv.vitor.entity.enums.Integrated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String username;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    private Integer integrated;
    private LocalDateTime lastModified;
    @Nonnull
    private Integer admin;

    @OneToMany(mappedBy = "users")
    private List<Ticket> tickets;

    public User() {

    }

    public User(Long id, String username, String email, String password, Integrated integrated, LocalDateTime lastModified, Admin admin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        setIntegrated(integrated);
        this.lastModified = lastModified;
        setAdmin(admin);
    }

    public Integer getIntegrated() {
        return integrated;
    }

    public void setIntegrated(Integrated integrated) {
        if(integrated != null) {
            this.integrated = integrated.getValue();
        }
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        if (admin != null) {
            this.admin = admin.getAdminValue();
        }
    }

}
