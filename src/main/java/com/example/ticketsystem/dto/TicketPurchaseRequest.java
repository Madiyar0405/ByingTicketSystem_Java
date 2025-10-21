package com.example.ticketsystem.dto;

import jakarta.validation.constraints.NotNull;

public record TicketPurchaseRequest(
        @NotNull Long passengerId,
        @NotNull Long carriageId
) {
}
