package com.techv.vitor.infrastructure.gateways;

import com.techv.vitor.application.gateways.TicketGateway;
import com.techv.vitor.domain.entity.Ticket;
import com.techv.vitor.infrastructure.persistence.TicketRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class TicketRepositoryGateway implements TicketGateway {

    private final TicketRepository ticketRepository;

    public TicketRepositoryGateway(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(Ticket ticket, JwtAuthenticationToken token) {
        return null;
    }

    @Override
    public Ticket agreeTicket(Long userId, Long ticketId, JwtAuthenticationToken token) {
        return null;
    }

    @Override
    public List<Ticket> findAll(JwtAuthenticationToken token) {
        return null;
    }

    @Override
    public Ticket findById(Long id, JwtAuthenticationToken token) {
        return null;
    }
}
