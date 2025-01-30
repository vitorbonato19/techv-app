package com.techv.vitor.controller.dto;

import java.time.LocalDateTime;

public class TicketRequestDto {

    private String requester;
    private Integer typeTicket;
    private String text;

    public TicketRequestDto() {

    }

    public TicketRequestDto(String requester, Integer typeTicket, String text) {
        this.requester = requester;
        this.typeTicket = typeTicket;
        this.text = text;
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

}
