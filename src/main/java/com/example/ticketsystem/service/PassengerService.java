package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.PassengerRequest;
import com.example.ticketsystem.dto.PassengerResponse;
import com.example.ticketsystem.model.Passenger;
import com.example.ticketsystem.model.PassengerType;
import com.example.ticketsystem.repository.PassengerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Transactional
    public PassengerResponse createPassenger(PassengerRequest request) {
        PassengerType type = PassengerType.fromAge(request.age());
        Passenger passenger = new Passenger(
                request.firstName(),
                request.lastName(),
                request.age(),
                request.balance(),
                type
        );
        Passenger saved = passengerRepository.save(passenger);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<PassengerResponse> findAll() {
        return passengerRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Passenger findEntityById(Long id) {
        return passengerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passenger %d not found".formatted(id)));
    }

    @Transactional
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public PassengerResponse toResponse(Passenger passenger) {
        Long carriageId = passenger.getCarriage() != null ? passenger.getCarriage().getId() : null;
        BigDecimal balance = passenger.getBalance();
        return new PassengerResponse(
                passenger.getId(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getAge(),
                passenger.getType(),
                balance,
                passenger.getSeatNumber(),
                carriageId
        );
    }
}
