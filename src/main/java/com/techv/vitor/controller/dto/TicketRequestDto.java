package com.techv.vitor.controller.dto;

import com.techv.vitor.entity.enums.TypeTicket;

import java.time.LocalDateTime;

public class TicketRequestDto {

    private String requester;
    private Integer typeTicket;
    private String text;
    private LocalDateTime createdAt;

    public TicketRequestDto() {

    }

    public TicketRequestDto(String requester, Integer typeTicket, String text, LocalDateTime createdAt) {
        this.requester = requester;
        this.typeTicket = typeTicket;
        this.text = text;
        this.createdAt = createdAt;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public Integer getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(Integer typeTicket) {
        this.typeTicket = typeTicket;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
