package com.techv.vitor.service;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.enums.TypeTicket;
import com.techv.vitor.exception.InvalidRequestException;
import com.techv.vitor.exception.TicketNotFoundException;
import com.techv.vitor.mapper.TicketMapper;
import com.techv.vitor.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserService userService;

    @Mock
    private TicketMapper ticketMapper;

    @InjectMocks
    private TicketService ticketService;

    @Nested
    class findAll {

        @Test
        @DisplayName("Should returnn a list of tickets")
        void shouldReturnAListOfTickets() {

            var mock = new Ticket.TicketBuilder().analyst("vitor").requester("teste").text("teste2text").reply("null").build();

            List<Ticket> tickets = new ArrayList<>();
            tickets.add(mock);

            Mockito.when(ticketService.findAll()).thenReturn(tickets);

            var response = ticketService.findAll();


            Assertions.assertAll(
                    () -> Assertions.assertNotNull(response),
                    () -> Assertions.assertEquals(response, tickets)
            );
        }

        @Test
        @DisplayName("Should return a empty list if the db is emtpy")
        void shouldReturnAEmptyList() {

            List<Ticket> tickets = new ArrayList<>();

            var response = ticketService.findAll();

            Assertions.assertAll(
                    () -> Assertions.assertEquals(response, tickets),
                    () -> Assertions.assertTrue(response.isEmpty())
            );

        }

        @Nested
        class findById {

            @Test
            @DisplayName("should return the ticket entity by the id on path params")
            void shouldReturnTicketEntityIfTheIdExists() {

                var mock = new Ticket.TicketBuilder()
                        .id(1L)
                        .analyst("vitor")
                        .requester("teste")
                        .text("teste2text")
                        .reply("null")
                        .build();

                Mockito.when(ticketRepository.findById(mock.getId())).thenReturn(Optional.of(mock));

                var response = ticketService.findById(mock.getId());

                Assertions.assertAll(
                        () -> Assertions.assertNotNull(response),
                        () -> Assertions.assertEquals(response.getId(), mock.getId()),
                        () -> Assertions.assertEquals(response.getAnalyst(), mock.getAnalyst()),
                        () -> Assertions.assertEquals(response.getRequester(), mock.getRequester()),
                        () -> Assertions.assertEquals(response.getText(), mock.getText()),
                        () -> Assertions.assertEquals(response.getReply(), mock.getReply())
                );
            }

            @Test
            @DisplayName("Should throw a exception if the id not exists on DB")
            void shouldThrowExceptionIfIdNotExists() {

                var mock = new Ticket.TicketBuilder()
                        .id(1L)
                        .analyst("vitor")
                        .requester("teste")
                        .text("teste2text")
                        .reply("null")
                        .build();

                Assertions.assertThrows(TicketNotFoundException.class, () -> ticketService.findById(mock.getId()));
            }
        }

        @Nested
        class createTicket {

            @Test
            @DisplayName("Should create a ticket and return response dto with data")
            void shouldCreateATicket() {

                var request = new TicketRequestDto(
                        "Vitor",
                        TypeTicket.FEATURE,
                        "text2test"
                );


                var mock = new Ticket.TicketBuilder()
                        .id(1L)
                        .analyst("vitor")
                        .requester(request.getRequester())
                        .text(request.getText())
                        .reply("null")
                        .type(request.getType())
                        .build();

                var responseDto = new TicketResponseDto(
                        mock.getId(),
                        mock.getRequester(),
                        mock.getText(),
                        mock.getType(),
                        mock.getCreatedAt(),
                        mock.isFinished()
                );

                Mockito.when(ticketMapper.toEntity(request)).thenReturn(mock);
                Mockito.when(ticketMapper.toResponseDto(mock)).thenReturn(responseDto);
                Mockito.when(ticketRepository.save(mock)).thenReturn(mock);

                var response = ticketService.createTicket(request);

                Assertions.assertAll(

                        () -> Assertions.assertNotNull(response),
                        () -> Assertions.assertEquals(response.getId(), mock.getId()),
                        () -> Assertions.assertEquals(response.getRequester(), mock.getRequester()),
                        () -> Assertions.assertEquals(response.getText(), mock.getText()),
                        () -> Assertions.assertEquals(response.getType(), mock.getType())
                );

                Mockito.verify(ticketRepository, Mockito.times(1)).save(Mockito.eq(mock));
            }

            @Test
            @DisplayName("Should throw a entity if requester fields from request may be null")
            void shouldThrowAExceptionIfRequesterFieldsMayBeNull() {

                var request = new TicketRequestDto(
                        null,
                        TypeTicket.FEATURE,
                        "text2test"
                );

                Assertions.assertThrows(InvalidRequestException.class, () -> ticketService.createTicket(request));
            }

            @Test
            @DisplayName("Should throw a entity if text field from request may be null")
            void shouldThrowAExceptionIfTextFieldsMayBeNull() {

                var request = new TicketRequestDto(
                        "Vitor",
                        TypeTicket.FEATURE,
                        null
                );

                Assertions.assertThrows(InvalidRequestException.class, () -> ticketService.createTicket(request));
            }


            @Test
            @DisplayName("Should throw a entity if type field from request may be null")
            void shouldThrowAExceptionIfTypeFieldMayBeNull() {

                var request = new TicketRequestDto(
                        "Vitor",
                        null,
                        "text2test"
                );

                Assertions.assertThrows(InvalidRequestException.class, () -> ticketService.createTicket(request));
            }
        }
    }
}