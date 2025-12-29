package com.ticketing.eventbooking.venue.repository;

import com.ticketing.eventbooking.venue.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {

    List<Seat> findByAuditoriumId(UUID auditoriumId);
}
