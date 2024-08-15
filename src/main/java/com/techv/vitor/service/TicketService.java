package com.techv.vitor.service;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.enums.TypeTicket;
import com.techv.vitor.exception.TicketCreatedException;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found...Verify the id... Id: " + id));
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

        responseDto.setText(ticket.getText());
        responseDto.setRequester(ticket.getRequester());
        responseDto.setTypeTicket(TypeTicket.FEATURE);
        responseDto.setCreatedAt(LocalDateTime.now());

        if (responseDto.getRequester() == null || responseDto.getTypeTicket() == null) {
            throw new TicketCreatedException("Have fields that can't be null...");
        }

        return responseDto;
    }

}
