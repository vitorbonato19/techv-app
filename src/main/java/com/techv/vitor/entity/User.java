package com.techv.vitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.enums.Integrated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles;

    @OneToMany(mappedBy = "users")
    private List<Ticket> tickets;

    public User() {

    }

    public User(Long id, String username, String email, String password, Integrated integrated, LocalDateTime lastModified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        setIntegrated(integrated);
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
