package com.techv.vitor.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.enums.TypeTicket;

import java.time.LocalDateTime;

public class TicketResponseDto {

    private Long id;
    private String requester;
    private String text;
    private TypeTicket type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    private boolean finished;


    public TicketResponseDto() {

    }

    public TicketResponseDto(Long id, String requester, String text, TypeTicket type, LocalDateTime createdAt, boolean finished) {
        this.id = id;
        this.requester = requester;
        this.text = text;
        this.type = type;
        this.createdAt = createdAt;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
