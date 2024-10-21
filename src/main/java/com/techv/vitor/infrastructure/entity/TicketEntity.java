package com.techv.vitor.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Finished;
import com.techv.vitor.entity.enums.TypeTicket;
import jakarta.persistence.*;
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
    @Column(columnDefinition = "INT (1) DEFAULT 0")
    private Integer typeTicket;
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
    private User users;

    public TicketEntity() {

    }

    public TicketEntity(Long id, String requester, String analyst, String description, String reply, TypeTicket typeTicket, LocalDateTime createdAt, LocalDateTime finishedAt, Finished finished, String status, User users) {
        this.id = id;
        this.requester = requester;
        this.analyst = analyst;
        this.description = description;
        this.reply = reply;
        this.status = status;
        setTypeTicket(typeTicket);
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        setFinished(finished);
        this.users = users;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeTicket(Integer typeTicket) {
        this.typeTicket = typeTicket;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
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

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getDescription() {
        return description;
    }

    public void setText(String description) {
        this.description = description;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Integer getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        if (typeTicket != null) {
            this.typeTicket = typeTicket.getTicketValue();
        }
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

    public void setFinished(Finished finished) {
        if (finished != null) {
            this.finished = finished.getValue();
        }
    }

    @JsonIgnore
    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
