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

    public static TypeTicket valueOf(int ticketValue) {
        for (TypeTicket t : TypeTicket.values()) {
            if (t.getTicketValue() == ticketValue) {
                return t;
            }
        }
        throw new IllegalArgumentException("Please set a valid type ticket code...");
    }
}
