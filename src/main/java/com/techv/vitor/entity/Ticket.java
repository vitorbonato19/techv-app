package com.techv.vitor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techv.vitor.entity.enums.Finished;
import com.techv.vitor.entity.enums.TypeTicket;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nonnull
    private String requester;
    @Nonnull
    private String analyst;
    @Column(columnDefinition = "TEXT")
    private String text;
    @Column(columnDefinition = "TEXT")
    private String reply;
    @Enumerated(EnumType.STRING)
    private TypeTicket type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime finishedAt;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean finished;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User users;

    public Ticket() {

    }

    public Ticket(Long id, String requester, String analyst, String text, String reply, TypeTicket type, LocalDateTime createdAt, LocalDateTime finishedAt, boolean finished, User users) {
        this.id = id;
        this.requester = requester;
        this.analyst = analyst;
        this.text = text;
        this.reply = reply;
        this.type = type;
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

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public TypeTicket getType() {
        return type;
    }

    public void setType(TypeTicket type) {
        this.type = type;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @JsonIgnore
    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
