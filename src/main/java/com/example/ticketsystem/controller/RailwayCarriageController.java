package com.example.ticketsystem.controller;

import com.example.ticketsystem.dto.RailwayCarriageRequest;
import com.example.ticketsystem.dto.RailwayCarriageResponse;
import com.example.ticketsystem.service.RailwayCarriageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carriages")
public class RailwayCarriageController {

    private final RailwayCarriageService carriageService;

    public RailwayCarriageController(RailwayCarriageService carriageService) {
        this.carriageService = carriageService;
    }

    @GetMapping
    public List<RailwayCarriageResponse> getCarriages() {
        return carriageService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RailwayCarriageResponse createCarriage(@Valid @RequestBody RailwayCarriageRequest request) {
        return carriageService.createCarriage(request);
    }
}
