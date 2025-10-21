package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.PassengerResponse;
import com.example.ticketsystem.model.Passenger;
import com.example.ticketsystem.model.PassengerType;
import com.example.ticketsystem.model.RailwayCarriage;
import com.example.ticketsystem.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TicketService {

    private final PassengerService passengerService;
    private final RailwayCarriageService carriageService;
    private final PassengerRepository passengerRepository;

    public TicketService(PassengerService passengerService,
                         RailwayCarriageService carriageService,
                         PassengerRepository passengerRepository) {
        this.passengerService = passengerService;
        this.carriageService = carriageService;
        this.passengerRepository = passengerRepository;
    }

    @Transactional
    public PassengerResponse purchaseTicket(Long passengerId, Long carriageId) {
        Passenger passenger = passengerService.findEntityById(passengerId);
        RailwayCarriage carriage = carriageService.findById(carriageId);

        if (passenger.getSeatNumber() != null && carriageId.equals(passenger.getCarriage() != null ? passenger.getCarriage().getId() : null)) {
            throw new IllegalStateException("Passenger already has a seat in this carriage");
        }

        List<Passenger> passengersInCarriage = carriage.getPassengers();
        long occupiedSeats = passengersInCarriage.stream()
                .filter(p -> p.getSeatNumber() != null)
                .count();
        if (occupiedSeats >= carriage.getCapacity()) {
            throw new IllegalStateException("No free seats available in carriage %d".formatted(carriage.getId()));
        }

        int seatNumber = nextAvailableSeat(passengersInCarriage, carriage.getCapacity());

        BigDecimal priceToPay = calculateTicketPrice(carriage.getBasePrice(), passenger.getType());
        if (passenger.getBalance().compareTo(priceToPay) < 0) {
            throw new IllegalStateException("Insufficient balance to buy ticket");
        }

        if (passenger.getCarriage() != null && passenger.getCarriage().getPassengers() != null) {
            passenger.getCarriage().getPassengers().removeIf(p -> p.getId().equals(passenger.getId()));
        }

        passenger.setBalance(passenger.getBalance().subtract(priceToPay));
        passenger.setCarriage(carriage);
        passenger.setSeatNumber(seatNumber);

        Passenger saved = passengerRepository.save(passenger);
        if (passengersInCarriage.stream().noneMatch(p -> p.getId().equals(saved.getId()))) {
            passengersInCarriage.add(saved);
        }
        return passengerService.toResponse(saved);
    }

    private int nextAvailableSeat(List<Passenger> passengers, int capacity) {
        Set<Integer> occupied = new HashSet<>();
        for (Passenger passenger : passengers) {
            if (passenger.getSeatNumber() != null) {
                occupied.add(passenger.getSeatNumber());
            }
        }
        for (int seat = 1; seat <= capacity; seat++) {
            if (!occupied.contains(seat)) {
                return seat;
            }
        }
        throw new IllegalStateException("No free seats available");
    }

    private BigDecimal calculateTicketPrice(BigDecimal basePrice, PassengerType type) {
        BigDecimal discount = basePrice.multiply(BigDecimal.valueOf(type.getDiscountFraction()));
        return basePrice.subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }
}
