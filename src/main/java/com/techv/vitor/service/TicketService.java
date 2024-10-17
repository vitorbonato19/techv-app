package com.techv.vitor.service;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.User;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.GenericException;
import com.techv.vitor.exception.TicketCreatedException;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.mapper.TicketMapper;
import com.techv.vitor.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final TicketMapper mapper;
    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, TicketMapper mapper, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public Page<Ticket> findAll(int page, int quantity, JwtAuthenticationToken token) {
        return ticketRepository.findAll(PageRequest.of(page, quantity));
    }

    public Ticket findById(Long id, JwtAuthenticationToken token) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found...Verify the id... Id: " + id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    public TicketResponseDto createTicket(TicketRequestDto requestDto, JwtAuthenticationToken token) {

        try {

            var ticket = mapper.toEntity(requestDto);

            ticketRepository.save(ticket);

            var responseDto = mapper.toResponseDto(ticket);

            if (responseDto.getRequester() == null || responseDto.getTypeTicket() == null) {
                throw new TicketCreatedException("Have fields that can't be null..." + "Requester: " + responseDto.getRequester() + " | TypeTicket: " + responseDto.getTypeTicket(), HttpStatus.PRECONDITION_FAILED);
            }

            return responseDto;

        } catch (GenericException ex) {
            throw new GenericException(
                    "Something was wrong...Verify your request",
                    HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public TicketResponseDto agreeTicket(Long userId, Long ticketId , JwtAuthenticationToken token) {

        var ticketResponse = new Ticket();
        var usernameResponse = new User();

        try {
            usernameResponse = userService.findById(userId);
            try {
                ticketResponse = findById(ticketId, token);
            } catch (TicketNotFoundException e) {
                throw new TicketNotFoundException("Ticket not found...Verify ticket id...",
                        HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("User not found...Verify the id:" + usernameResponse.getId(),
                    HttpStatus.NOT_FOUND);
        }

        ticketResponse.setAnalyst(usernameResponse.getUsername());
        ticketResponse.setUsers(usernameResponse);
        ticketRepository.save(ticketResponse);

        return mapper.toResponseDto(ticketResponse);
    }

    @Transactional
    public void deleteTicketById(Long id, JwtAuthenticationToken token) {

        var ticketResponse = ticketRepository.findById(id);

        if (!ticketResponse.isPresent()) {
            throw new TicketNotFoundException("Ticket not found...Verify the id..." + id , HttpStatus.NOT_FOUND);
        }

        ticketRepository.deleteById(id);
    }
}
