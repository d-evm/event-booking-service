package com.ticketing.eventbooking.event.repository;

import com.ticketing.eventbooking.event.model.Event;
import com.ticketing.eventbooking.event.model.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByActiveTrue();

    List<Event> findByCategoryAndActiveTrue(EventCategory category);

    List<Event> findByLanguageIgnoreCaseAndActiveTrue(String language);
}
