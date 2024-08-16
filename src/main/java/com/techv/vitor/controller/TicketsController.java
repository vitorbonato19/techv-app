package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.User;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketsController {

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    public TicketsController(TicketRepository ticketRepository, TicketService ticketService) {
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> findAll() {
        var tickets = ticketRepository.findAll();
        return ResponseEntity.ok().body(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id) {
         var ticketsById = ticketRepository.findById(id).orElseThrow(() ->
                 new TicketNotFoundException(
                         "Ticket nout found, verify your id...",
                         HttpStatus.NOT_FOUND));
        return ResponseEntity.ok().body(ticketsById);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        var response = ticketService.createTicket(ticketRequestDto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(response.getId())
                .toUri()).body(response);
    }

    @PutMapping("/agree/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Ticket> agreeTicket(@RequestBody User username, @PathVariable  Long ticketId) {
        var response = ticketService.agreeTicket(username, ticketId);
        return ResponseEntity.accepted().body(response);
    }

}
