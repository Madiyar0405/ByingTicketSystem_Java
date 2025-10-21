package com.example.ticketsystem.dto;

import java.math.BigDecimal;

public record RailwayCarriageResponse(
        Long id,
        String name,
        int capacity,
        BigDecimal basePrice,
        long occupiedSeats
) {
}
