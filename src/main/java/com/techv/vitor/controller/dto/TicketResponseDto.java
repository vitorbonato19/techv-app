package com.techv.vitor.controller.dto;

import com.techv.vitor.entity.enums.TypeTicket;

import java.time.LocalDateTime;

public class TicketResponseDto {

    private Long id;
    private String requester;
    private String analyst;
    private String text;
    private String reply;
    private Integer typeTicket;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private Integer finished;

    public TicketResponseDto() {

    }

    public TicketResponseDto(Long id, String requester, String text, String reply, Integer typeTicket, Integer finished) {
        this.id = id;
        this.requester = requester;
        this.text = text;
        this.reply = reply;
        this.typeTicket = typeTicket;
        this.finished = finished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTypeTicket(Integer typeTicket) {
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

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
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

    public Integer getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        if (typeTicket != null) {
            this.typeTicket = typeTicket.getTicketValue();
        }
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }
}
