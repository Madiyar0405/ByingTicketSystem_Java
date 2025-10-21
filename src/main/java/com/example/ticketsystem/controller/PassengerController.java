package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.PassengerRequest;
import com.example.ticketsystem.dto.PassengerResponse;
import com.example.ticketsystem.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<PassengerResponse> getPassengers() {
        return passengerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PassengerResponse createPassenger(@Valid @RequestBody PassengerRequest request) {
        return passengerService.createPassenger(request);
    }
}
