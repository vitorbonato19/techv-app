package com.techv.vitor.controller.dto;

import java.time.LocalDateTime;

public class TicketResponseDto {

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

    public TicketResponseDto(String requester, String text, String reply, Integer typeTicket, Integer finished) {
        this.requester = requester;
        this.text = text;
        this.reply = reply;
        this.typeTicket = typeTicket;
        this.finished = finished;
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

    public void setTypeTicket(Integer typeTicket) {
        this.typeTicket = typeTicket;
    }

    public Integer getFinished() {
        return finished;
    }

    public void setFinished(Integer finished) {
        this.finished = finished;
    }
}
