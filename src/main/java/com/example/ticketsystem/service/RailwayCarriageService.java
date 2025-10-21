package com.example.ticketsystem.service;

import com.example.ticketsystem.dto.RailwayCarriageRequest;
import com.example.ticketsystem.dto.RailwayCarriageResponse;
import com.example.ticketsystem.model.RailwayCarriage;
import com.example.ticketsystem.repository.RailwayCarriageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RailwayCarriageService {

    private final RailwayCarriageRepository carriageRepository;

    public RailwayCarriageService(RailwayCarriageRepository carriageRepository) {
        this.carriageRepository = carriageRepository;
    }

    @Transactional
    public RailwayCarriageResponse createCarriage(RailwayCarriageRequest request) {
        RailwayCarriage carriage = new RailwayCarriage(
                request.name(),
                request.capacity(),
                request.basePrice()
        );
        RailwayCarriage saved = carriageRepository.save(carriage);
        return mapToResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<RailwayCarriageResponse> findAll() {
        return carriageRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public RailwayCarriage findById(Long id) {
        return carriageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carriage %d not found".formatted(id)));
    }

    private RailwayCarriageResponse mapToResponse(RailwayCarriage carriage) {
        long occupiedSeats = carriage.getPassengers().stream()
                .filter(passenger -> passenger.getSeatNumber() != null)
                .count();
        return new RailwayCarriageResponse(
                carriage.getId(),
                carriage.getName(),
                carriage.getCapacity(),
                carriage.getBasePrice(),
                occupiedSeats
        );
    }
}
