package com.techv.vitor.service;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.exception.InvalidRequestException;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.mapper.TicketMapper;
import com.techv.vitor.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TicketService {


    private final TicketRepository ticketRepository;

    private final UserService userService;

    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, UserService userService, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
        this.ticketMapper = ticketMapper;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        var entity = ticketRepository.findById(id);
        if (entity.isPresent()) {
            return entity;
        }
        throw new TicketNotFoundException("Ticket not found...Verify the id... Id: " + id, HttpStatus.NOT_FOUND);
    }

    @Transactional
    public TicketResponseDto createTicket(TicketRequestDto requestDto) {

        try {

            if (requestDto.getRequester() == null || requestDto.getRequester().equalsIgnoreCase(" ")) {
                throw new InvalidRequestException("requester field cant be null", HttpStatus.BAD_REQUEST);
            }

            var entity = ticketMapper.toEntity(requestDto);
            ticketRepository.save(entity);
            var response = ticketMapper.toResponseDto(entity);

            return response;

        } catch (InvalidRequestException ex) {
            throw new InvalidRequestException("during request something wrong happened ", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public TicketResponseDto agreeTicket(Long userId, Long ticketId) {

        try {

            var user = userService.findById(userId);
            var ticket = findById(ticketId);

            if (Objects.equals(user.getId(), ticket.get().getId())) {
                throw new InvalidRequestException("ticket alredy's have a analyst.", HttpStatus.CONFLICT);
            }

            ticket.get().setAnalyst(user.getUsername());
            ticket.get().setUsers(user);

            ticketRepository.save(ticket.get());
            var response = ticketMapper.toResponseDto(ticket.get());

            return response;

        } catch (InvalidRequestException ex) {
            throw new InvalidRequestException("during request something wrong happened ", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void deleteTicketById(Long id) {

        var ticketResponse = ticketRepository.findById(id);

        if (!ticketResponse.isPresent()) {
            throw new TicketNotFoundException("Ticket not found...Verify the id..." + id , HttpStatus.NOT_FOUND);
        }

        ticketRepository.deleteById(id);
    }
}
