package com.techv.vitor.service;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Finished;
import com.techv.vitor.entity.enums.TypeTicket;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.TicketCreatedException;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserService userService;

    public TicketService(TicketRepository ticketRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public List<Ticket> findAll(JwtAuthenticationToken token) {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id, JwtAuthenticationToken token) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found...Verify the id... Id: " + id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    public TicketResponseDto createTicket(TicketRequestDto requestDto, JwtAuthenticationToken token) {

        Ticket ticket = new Ticket();

        ticket.setRequester(requestDto.getRequester());
        ticket.setTypeTicket(TypeTicket.FEATURE);
        ticket.setText(requestDto.getText());
        ticket.setCreatedAt(LocalDateTime.now());

        ticketRepository.save(ticket);

        TicketResponseDto responseDto = new TicketResponseDto();

        responseDto.setId(ticket.getId());
        responseDto.setStatus(HttpStatus.CREATED);
        responseDto.setStatusCode(HttpStatus.CREATED);
        responseDto.setFinished(Finished.FALSE.getValue());

        responseDto.setText(ticket.getText());
        responseDto.setRequester(ticket.getRequester());
        responseDto.setTypeTicket(TypeTicket.FEATURE);
        responseDto.setCreatedAt(LocalDateTime.now());

        if (responseDto.getRequester() == null || responseDto.getTypeTicket() == null) {
            throw new TicketCreatedException("Have fields that can't be null..." + "Requester: " + responseDto.getRequester() + " | TypeTicket: " + responseDto.getTypeTicket(), HttpStatus.PRECONDITION_FAILED);
        }

        return responseDto;
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

        TicketResponseDto ticketResponseDto = new TicketResponseDto();

        ticketResponseDto.setId(ticketResponse.getId());
        ticketResponseDto.setStatus(HttpStatus.OK);
        ticketResponseDto.setStatusCode(HttpStatus.OK);
        ticketResponseDto.setRequester(ticketResponse.getRequester());

        return ticketResponseDto;
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
