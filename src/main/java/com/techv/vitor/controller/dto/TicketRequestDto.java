package com.techv.vitor.controller.dto;

import com.techv.vitor.entity.enums.TypeTicket;

public class TicketRequestDto {

    private String requester;
    private TypeTicket type;
    private String text;

    public TicketRequestDto() {

    }

    public TicketRequestDto(String requester, TypeTicket type, String text) {
        this.requester = requester;
        this.type = type;
        this.text = text;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public TypeTicket getType() {
        return type;
    }

    public void setType(TypeTicket type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
