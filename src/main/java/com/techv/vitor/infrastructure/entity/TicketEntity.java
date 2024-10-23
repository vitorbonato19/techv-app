package com.techv.vitor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.infrastructure.entity.enums.TypeTicket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String requester;
    @NotBlank
    @Column(columnDefinition = "VARCHAR (15) DEFAULT 'OPEN'")
    private String status;
    @NotBlank
    @Column(columnDefinition = "VARCHAR (15) DEFAULT NULL")
    private String analyst;
    @NotBlank
    @Column(columnDefinition = "VARCHAR (250) DEFAULT NULL")
    private String description;
    @NotBlank
    @Column(columnDefinition = "VARCHAR (250) DEFAULT NULL")
    private String reply;
    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT '2000-01-01 00:00:00'")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP DEFAULT '2000-01-01 00:00:00'")
    private LocalDateTime finishedAt;

    @Column(nullable = true, columnDefinition = "INT DEFAULT 0", length = 1)
    private Integer finished;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity users;

    public TicketEntity() {

    }

    public TicketEntity(Long id, String requester, String status, String analyst, String description, String reply, TypeTicket typeTicket, LocalDateTime createdAt, LocalDateTime finishedAt, Integer finished, UserEntity users) {
        this.id = id;
        this.requester = requester;
        this.status = status;
        this.analyst = analyst;
        this.description = description;
        this.reply = reply;
        this.typeTicket = typeTicket;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.finished = finished;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }
}
