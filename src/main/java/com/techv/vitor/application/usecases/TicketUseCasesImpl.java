package com.techv.vitor.application.usecases;

import com.techv.vitor.application.entity.Ticket;
import com.techv.vitor.application.gateways.TicketGateway;

import java.util.List;

public class TicketUseCasesImpl implements TicketUseCases {

    private final TicketGateway ticketGateway;

    public TicketUseCasesImpl(TicketGateway ticketGateway) {
        this.ticketGateway = ticketGateway;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketGateway.createTicket(ticket);
    }

    @Override
    public Ticket agreeTicket(Long userId, Long ticketId) {
        return ticketGateway.agreeTicket(userId, ticketId);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketGateway.findAll();
    }

    @Override
    public Ticket findById(Long id) {
        return ticketGateway.findById(id);
    }
}
