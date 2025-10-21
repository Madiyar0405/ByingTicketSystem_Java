package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.PassengerRequest;
import com.example.ticketsystem.dto.PassengerResponse;
import com.example.ticketsystem.model.RailwayCarriage;
import com.example.ticketsystem.repository.RailwayCarriageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TicketServiceTest {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private RailwayCarriageRepository carriageRepository;

    private Long carriageId;

    @BeforeEach
    void setUp() {
        RailwayCarriage carriage = carriageRepository.save(new RailwayCarriage("Test", 2, BigDecimal.valueOf(100)));
        carriageId = carriage.getId();
    }

    @Test
    void purchaseTicketAssignsSeatAndChargesBalance() {
        PassengerResponse passenger = passengerService.createPassenger(
                new PassengerRequest("John", "Doe", 30, BigDecimal.valueOf(200))
        );

        PassengerResponse updated = ticketService.purchaseTicket(passenger.id(), carriageId);

        assertThat(updated.seatNumber()).isNotNull();
        assertThat(updated.carriageId()).isEqualTo(carriageId);
        assertThat(updated.balance()).isEqualByComparingTo("100.00");
    }
}
