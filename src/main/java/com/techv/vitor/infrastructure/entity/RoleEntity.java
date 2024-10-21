package com.techv.vitor.infrastructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String name;

    public RoleEntity() {

    }

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Values {

        ADMIN(1L),
        NOT_ADMIN(2L);

        long roleId;

        Values(long roleId) {
                this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}
