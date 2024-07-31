package com.techv.vitor.entity.enums;

public enum TypeTicket {
    PC_MAINTENCE(0),
    NETWORK_ERROR(1),
    FEATURE(2),
    ANOTHER(3);

    private final int ticketValue;

    TypeTicket(int ticketValue) {
        this.ticketValue = ticketValue;
    }

    public int getTicketValue() {
        return ticketValue;
    }
}
