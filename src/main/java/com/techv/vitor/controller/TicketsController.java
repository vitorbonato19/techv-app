package com.techv.vitor.controller;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Data;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.service.TicketService;
import org.springframework.http.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/tickets")
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
    public ResponseEntity<Data<List<Ticket>>> findAll(@RequestHeader("Authorization") String token) {
        var tickets = ticketRepository.findAll();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.GET));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<Ticket>> findById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        var tickets = ticketService.findById(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.GET));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Data<TicketResponseDto>> createTicket(@RequestHeader("Authorization") String token, @RequestBody TicketRequestDto ticketRequestDto) {
        var tickets = ticketService.createTicket(ticketRequestDto);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.POST));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
    }

    @PutMapping("/agree/{user}/{ticket}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Data<TicketResponseDto>> agreeTicket(@RequestHeader("Authorization") String token, @PathVariable Long user, @PathVariable Long ticket) {
        var tickets = ticketService.agreeTicket(user, ticket);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.PUT));
        var headerData = data.convertToMap(headers);
        var response = new Data<>(tickets, headerData);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ResponseEntity<Void>> deleteTicketById(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        ticketService.deleteTicketById(id);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setDate(Instant.now());
        headers.setAccessControlAllowMethods(List.of(HttpMethod.DELETE));
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
