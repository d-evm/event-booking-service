package com.ticketing.eventbooking.venue.repository;

import com.ticketing.eventbooking.venue.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VenueRepository extends JpaRepository<Venue, UUID> {

    List<Venue> findByCityIgnoreCase(String city);
}
