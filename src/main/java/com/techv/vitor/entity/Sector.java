package com.techv.vitor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Sector() {

    }

    public Sector(Long id, String name) {
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

        TI (1L),
        COMMERCIAL(2L),
        ECOMMERCE(3L),
        SUPPLY(4L);

        long sectorId;

        Values(Long sectorId) {
            this.sectorId = sectorId;
        }

        public long getSectorId() {
            return sectorId;
        }
    }

}
