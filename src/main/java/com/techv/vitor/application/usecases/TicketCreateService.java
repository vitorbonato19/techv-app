package com.techv.vitor.application.usecases;

import com.techv.vitor.domain.entity.Ticket;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class TicketCreateService implements CreateTicket {

    @Override
    public Ticket createTicket(Ticket ticket, JwtAuthenticationToken token) {
        return null;
    }
}
