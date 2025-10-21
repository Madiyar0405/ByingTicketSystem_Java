package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.PassengerResponse;
import com.example.ticketsystem.dto.TicketPurchaseRequest;
import com.example.ticketsystem.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerResponse purchaseTicket(@Valid @RequestBody TicketPurchaseRequest request) {
        return ticketService.purchaseTicket(request.passengerId(), request.carriageId());
    }
}
