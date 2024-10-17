package com.techv.vitor.application.gateways;

import com.techv.vitor.domain.entity.Ticket;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface TicketGateway {

    Ticket createTicket(Ticket ticket, JwtAuthenticationToken token);

    Ticket agreeTicket(Long userId, Long ticketId, JwtAuthenticationToken token);

    List<Ticket> findAll(JwtAuthenticationToken token);

    Ticket findById(Long id, JwtAuthenticationToken token);

}
