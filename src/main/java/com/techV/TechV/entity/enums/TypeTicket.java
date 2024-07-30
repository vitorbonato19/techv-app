package com.techV.TechV.entity.enums;

import java.lang.reflect.Type;

public enum TypeTicket {

    PC_MAINTENCE(0),
    NETWORK_ERROR(1),
    FEATURE(2),
    ANOTHER(3);

    private final int typeTicketNumber;

    TypeTicket(int typeTicketNumber) {
        this.typeTicketNumber = typeTicketNumber;
    }

    public int getTypeTicketNumber() {
        return typeTicketNumber;
    }


}
