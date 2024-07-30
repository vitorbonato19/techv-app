package com.techV.TechV.entity;

import com.techV.TechV.entity.enums.TypeTicket;

import java.time.LocalDateTime;

public class Ticket {

    private Long id;
    private Usuario requester;
    private Usuario analyst;
    private String text;
    private String reply;
    private TypeTicket typeTicket;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private Boolean open;
    private Boolean finished;

    public Ticket() {

    }

    public Ticket(Long id, Usuario requester, Usuario analyst, String text, String reply, TypeTicket typeTicket, LocalDateTime finishedAt, LocalDateTime createdAt, Boolean open, Boolean finished) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getRequester() {
        return requester;
    }

    public void setRequester(Usuario requester) {
        this.requester = requester;
    }

    public Usuario getAnalyst() {
        return analyst;
    }

    public void setAnalyst(Usuario analyst) {
        this.analyst = analyst;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
