package com.ticketing.eventbooking.show.repository;

import com.ticketing.eventbooking.show.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ShowRepository extends JpaRepository<Show, UUID> {

    List<Show> findByEventId(UUID eventId);

    List<Show> findByAuditoriumVenueCityIgnoreCase(String city);

    List<Show> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    boolean existsByAuditoriumIdAndStartTimeLessThanAndEndTimeGreaterThan(
            UUID auditoriumId,
            LocalDateTime end,
            LocalDateTime start
    );
}
