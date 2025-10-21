package com.example.ticketsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record RailwayCarriageRequest(
        @NotBlank String name,
        @Positive int capacity,
        @NotNull @Positive BigDecimal basePrice
) {
}
