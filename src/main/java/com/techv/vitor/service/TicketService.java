package com.techv.vitor.service;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.User;
import com.techv.vitor.entity.enums.Finished;
import com.techv.vitor.entity.enums.TypeTicket;
import com.techv.vitor.exception.EntityNotFoundException;
import com.techv.vitor.exception.NotAdminException;
import com.techv.vitor.exception.TicketCreatedException;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.repository.TicketRepository;
import com.techv.vitor.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found...Verify the id... Id: " + id, HttpStatus.NOT_FOUND));
    }

    @Transactional
    public TicketResponseDto createTicket(TicketRequestDto requestDto) {

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
            throw new TicketCreatedException("Have fields that can't be null...", HttpStatus.PRECONDITION_FAILED);
        }

        return responseDto;
    }

   /* @Transactional
    public Ticket agreeTicket(UUID userId, Long ticketId) {

        Ticket ticket = new Ticket();

        var ticketResponse = ticketRepository.findById(ticketId);
        var usernameResponse = userRepository.findById(userId);

        if (ticketResponse.isPresent()) {
            if(usernameResponse.isPresent()) {

                ticket.setAnalyst(username.getUsername());
                ticket.setUsers(username);
                ticketRepository.save(ticket);
                return ticket;

            }
        }

        throw new TicketNotFoundException("Ticket not found...Verify ticket id...", HttpStatus.NOT_FOUND);
    }*/

}
