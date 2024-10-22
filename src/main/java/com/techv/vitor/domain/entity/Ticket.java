package com.techv.vitor.domain.entity;

public record Ticket(User requester,
                     User analysts,
                     String description,
                     String problem,
                     String reply,
                     String status,
                     Integer finished) {
}
