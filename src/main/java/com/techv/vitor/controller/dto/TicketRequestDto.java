package com.techv.vitor.controller.dto;

import java.time.LocalDateTime;

public class TicketRequestDto {

    private String requester;
    private Integer typeTicket;
    private String description;

    public TicketRequestDto() {

    }

    public TicketRequestDto(String requester, Integer typeTicket, String description) {
        this.requester = requester;
        this.typeTicket = typeTicket;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
