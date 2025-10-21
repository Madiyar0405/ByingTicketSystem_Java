package com.example.ticketsystem.repository;

import com.example.ticketsystem.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    long countByCarriageId(Long carriageId);

    @Query("select p from Passenger p where p.carriage.id = :carriageId and p.seatNumber = :seat")
    Optional<Passenger> findByCarriageIdAndSeatNumber(Long carriageId, Integer seat);
}
