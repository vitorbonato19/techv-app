package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Data;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.service.TicketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketsController {

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    private final Data data;

    private final HttpHeaders headers;

    public TicketsController(TicketRepository ticketRepository, TicketService ticketService, Data data, HttpHeaders headers) {
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
        this.data = data;
        this.headers = headers;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<List<Ticket>>> findAll() {
        var tickets = ticketRepository.findAll();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<Ticket>> findById(@PathVariable Long id) {
         var tickets = ticketRepository.findById(id).orElseThrow(() ->
                 new TicketNotFoundException(
                         "Ticket nout found, verify your id...",
                         HttpStatus.NOT_FOUND));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<TicketResponseDto>> createTicket(@RequestBody TicketRequestDto ticketRequestDto, JwtAuthenticationToken token) {
        var tickets = ticketService.createTicket(ticketRequestDto, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.add("userID", "" + tickets.getId());
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping("/agree/{userUUID}/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<TicketResponseDto>> agreeTicket(@PathVariable Long userId, @PathVariable Long ticketId, JwtAuthenticationToken token) {
        var tickets = ticketService.agreeTicket(userId, ticketId, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseEntity<Void>> deleteTicketById(@PathVariable Long id, JwtAuthenticationToken token) {
        ticketService.deleteTicketById(id, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("issuer", "api-techv.java");
        headers.setDate(Instant.now());
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
