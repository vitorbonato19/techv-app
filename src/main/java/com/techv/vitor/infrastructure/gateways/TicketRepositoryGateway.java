package com.techv.vitor.infrastructure.gateways;

import com.techv.vitor.application.gateways.TicketGateway;
import com.techv.vitor.application.entity.Ticket;
import com.techv.vitor.infrastructure.persistence.TicketRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class TicketRepositoryGateway implements TicketGateway {

    @Override
    public Ticket createTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket agreeTicket(Long userId, Long ticketId) {
        return null;
    }

    @Override
    public List<Ticket> findAll() {
        return null;
    }

    @Override
    public Ticket findById(Long id) {
        return null;
    }
}
