package com.techv.vitor.controller;

import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.User;
import com.techv.vitor.repository.TicketRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketsController {

    private final TicketRepository ticketRepository;

    public TicketsController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    @RequestMapping(value = "/api/v1/tickets")
    public ResponseEntity<List<Ticket>> findAll() {
        var tickets = ticketRepository.findAll();
        return ResponseEntity.ok().body(tickets);
    }

    @GetMapping
    @RequestMapping(value = "/api/v1/tickets/{id}")
    public ResponseEntity<Optional<Ticket>> findById(@PathVariable Long id) {
        var ticketsById = ticketRepository.findById(id);
        return ResponseEntity.ok().body(ticketsById);
    }


}
