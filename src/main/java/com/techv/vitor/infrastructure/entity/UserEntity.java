package com.techv.vitor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.Roles;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.enums.Integrated;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String zipCode;
    private Integer integrated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "usersroles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "users")
    private List<TicketEntity> tickets;

    public UserEntity() {

    }

    public UserEntity(String username, String email, String password, String zipCode, Integer integrated, LocalDateTime lastModified, Set<RoleEntity> roles, List<TicketEntity> tickets) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.zipCode = zipCode;
        this.integrated = integrated;
        this.lastModified = lastModified;
        this.roles = roles;
        this.tickets = tickets;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private String zipCode;
        private Integer integrated;
        private LocalDateTime lastModified;
        private Set<RoleEntity> role;
        private List<TicketEntity> tickets;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
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

        public Builder integrated(Integer integrated) {
            this.integrated = integrated;
            return this;
        }

        public Builder lastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder role(Set<RoleEntity> role) {
            this.role = role;
            return this;
        }

        public Builder tickets(List<TicketEntity> tickets) {
            this.tickets = tickets;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(username, email, password, zipCode, integrated, lastModified, role, tickets);
        }
    }



    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setIntegrated(Integer integrated) {
        this.integrated = integrated;
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

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
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

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
