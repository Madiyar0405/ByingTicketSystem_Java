package com.example.ticketsystem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PassengerRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Min(0) int age,
        @NotNull @Positive BigDecimal balance
) {
}
