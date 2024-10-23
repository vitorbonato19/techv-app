package com.techv.vitor.application.usecases;

import com.techv.vitor.application.entity.Ticket;

import java.util.List;

public interface TicketUseCases {

    Ticket createTicket(Ticket ticket);

    Ticket agreeTicket(Long userId, Long ticketId);

    List<Ticket> findAll();

    Ticket findById(Long id);
}
