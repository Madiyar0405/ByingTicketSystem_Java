package com.example.ticketsystem.dto;

import com.example.ticketsystem.model.PassengerType;

import java.math.BigDecimal;

public record PassengerResponse(
        Long id,
        String firstName,
        String lastName,
        int age,
        PassengerType type,
        BigDecimal balance,
        Integer seatNumber,
        Long carriageId
) {
}
