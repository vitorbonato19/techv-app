package com.techv.vitor.entity;

import com.techv.vitor.entity.enums.TypeTicket;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TICKETS")
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
    private TypeTicket typeTicket;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private Boolean open;
    private Boolean finished;

    @ManyToOne
    private User users;

    public Ticket() {

    }

    public Ticket(Long id, String requester, String analyst, String text, String reply, TypeTicket typeTicket, LocalDateTime finishedAt, LocalDateTime createdAt, Boolean open, Boolean finished) {
        this.id = id;
        this.requester = requester;
        this.analyst = analyst;
        this.text = text;
        this.reply = reply;
        this.typeTicket = typeTicket;
        this.finishedAt = finishedAt;
        this.createdAt = createdAt;
        this.open = open;
        this.finished = finished;
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

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Long getId() {
        return id;
    }
}
