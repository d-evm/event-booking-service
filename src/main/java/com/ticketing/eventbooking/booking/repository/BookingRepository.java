package com.ticketing.eventbooking.booking.repository;

import com.ticketing.eventbooking.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByUserId(UUID userId);

    List<Booking> findByShowId(UUID showId);
}
