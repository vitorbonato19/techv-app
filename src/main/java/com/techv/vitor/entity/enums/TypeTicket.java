package com.techv.vitor.entity.enums;

public enum TypeTicket {
    PC_MAINTENCE(1),
    NETWORK_ERROR(2),
    FEATURE(3),
    ANOTHER(4);

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
