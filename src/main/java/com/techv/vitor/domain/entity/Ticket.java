package com.techv.vitor.domain.entity;

import com.techv.vitor.entity.enums.TypeTicket;

public record Ticket(User requester,
                     User analysts,
                     String description,
                     String problem,
                     String reply,
                     String status,
                     Integer finished) {
}
