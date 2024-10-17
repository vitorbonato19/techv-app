package com.techv.vitor.application.usecases;

import com.techv.vitor.domain.entity.Ticket;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class TicketAgreeService implements AgreeTicket {

    @Override
    public Ticket agreeTicket(Long userId, Long ticketId, JwtAuthenticationToken token) {
        return null;
    }
}
