package com.techv.vitor.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.enums.TypeTicket;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class TicketResponseDto {

    private Long id;
    private HttpStatus status;
    private HttpStatus statusCode;
    private String requester;
    private String text;
    private Integer typeTicket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private Integer finished;

    public TicketResponseDto() {

    }

    public TicketResponseDto(Long id, HttpStatus status, HttpStatus statusCode, String requester, String text,Integer typeTicket, Integer finished) {
        this.id = id;
        this.status = status;
        this.statusCode = statusCode;
        this.requester = requester;
        this.text = text;
        this.typeTicket = typeTicket;
        this.finished = finished;
    }

    public int getStatusCode() {
        return statusCode.value();
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
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
