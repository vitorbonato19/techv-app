package com.techv.vitor.infrastructure.controller;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.infrastructure.entity.Data;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.service.TicketService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;

    private final TicketService ticketService;

    private final Data data;

    private final HttpHeaders headers;

    public TicketController(TicketRepository ticketRepository, TicketService ticketService, Data data, HttpHeaders headers) {
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
        this.data = data;
        this.headers = headers;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Data<List<Ticket>>> findAll(@RequestParam int page,
                                                      @RequestParam int quantity,
                                                      JwtAuthenticationToken token) {
        var tickets = ticketService.findAll(page, quantity, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Issuer", "api-techv.java");
        headers.add("Status", "" + HttpStatus.OK.value());
        headers.add("StatusCode", "" + HttpStatus.OK);
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<Ticket>> findById(@PathVariable Long id, JwtAuthenticationToken token) {
        var tickets = ticketService.findById(id, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Issuer", "api-techv.java");
        headers.add("Status", "" + HttpStatus.OK.value());
        headers.add("StatusCode", "" + HttpStatus.OK);
        headers.add("Auth-Token", "" + token.getToken().getTokenValue());
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<TicketResponseDto>> createTicket(@RequestBody TicketRequestDto ticketRequestDto, JwtAuthenticationToken token) {
        var tickets = ticketService.createTicket(ticketRequestDto, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Issuer", "api-techv.java");
        headers.add("Ticket-Id", "" + tickets.getId());
        headers.add("Status", "" + HttpStatus.CREATED.value());
        headers.add("StatusCode", "" + HttpStatus.CREATED);
        headers.add("Auth-Token", "" + token.getToken().getTokenValue());
        headers.setDate(Instant.now());
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping("/agree/{userId}/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<TicketResponseDto>> agreeTicket(@PathVariable Long userId, @PathVariable Long ticketId, JwtAuthenticationToken token) {
        var tickets = ticketService.agreeTicket(userId, ticketId, token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Issuer", "api-techv.java");
        headers.add("Status", "" + HttpStatus.OK.value());
        headers.add("StatusCode", "" + HttpStatus.OK);
        headers.add("Auth-Token", "" + token.getToken().getTokenValue());
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
        headers.add("Issuer", "api-techv.java");
        headers.add("Status", "" + HttpStatus.NO_CONTENT.value());
        headers.add("StatusCode", "" + HttpStatus.NO_CONTENT);
        headers.add("Auth-Token", "" + token.getToken().getTokenValue());
        headers.setDate(Instant.now());
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
