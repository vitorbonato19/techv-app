package com.techv.vitor.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.techv.vitor.entity.enums.TypeTicket;

import java.time.LocalDateTime;

public class TicketResponseDto {

    private Long id;
    private String requester;
    private String description;
    private Integer typeTicket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private Integer finished;

    public TicketResponseDto() {

    }

    public TicketResponseDto(Long id, String requester, String description,Integer typeTicket, Integer finished) {
        this.id = id;
        this.requester = requester;
        this.description = description;
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

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
