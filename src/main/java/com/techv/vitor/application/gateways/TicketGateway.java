package com.techv.vitor.application.gateways;

import com.techv.vitor.application.entity.Ticket;

import java.util.List;

public interface TicketGateway {

    Ticket createTicket(Ticket ticket);

    Ticket agreeTicket(Long userId, Long ticketId);

    List<Ticket> findAll();

    Ticket findById(Long id);

}
