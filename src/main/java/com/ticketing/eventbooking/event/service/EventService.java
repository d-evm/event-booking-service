package com.ticketing.eventbooking.event.service;

import com.ticketing.eventbooking.event.dto.CreateEventRequest;
import com.ticketing.eventbooking.event.model.Event;
import com.ticketing.eventbooking.event.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Event create(CreateEventRequest request) {

        Event event = new Event(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getLanguage(),
                request.getGenre(),
                request.getDurationMinutes(),
                request.getRating()
        );

        return repository.save(event);
    }

    @Transactional(readOnly = true)
    public List<Event> getActiveEvents() {
        return repository.findByActiveTrue();
    }

    @Transactional
    public void deactivate(UUID eventId) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.deactivate();
    }
}
