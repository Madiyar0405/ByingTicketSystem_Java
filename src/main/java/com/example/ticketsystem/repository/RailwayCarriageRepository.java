package com.example.ticketsystem.repository;

import com.example.ticketsystem.model.RailwayCarriage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailwayCarriageRepository extends JpaRepository<RailwayCarriage, Long> {
}
