package com.techv.vitor.service;

import com.techv.vitor.entity.Ticket;
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

    }
}