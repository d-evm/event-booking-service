package com.ticketing.eventbooking.venue.repository;

import com.ticketing.eventbooking.venue.model.Auditorium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuditoriumRepository extends JpaRepository<Auditorium, UUID> {

    List<Auditorium> findByVenueId(UUID venueId);
}
