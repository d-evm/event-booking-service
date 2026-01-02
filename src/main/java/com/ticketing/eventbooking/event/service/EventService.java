package com.ticketing.eventbooking.event.service;

import com.ticketing.eventbooking.event.dto.CreateEventRequest;
import com.ticketing.eventbooking.event.dto.EventResponse;
import com.ticketing.eventbooking.event.model.Event;
import com.ticketing.eventbooking.event.repository.EventRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

//    @Transactional
//    public Event create(CreateEventRequest request) {
//
//        Event event = new Event(
//                request.getTitle(),
//                request.getDescription(),
//                request.getCategory(),
//                request.getLanguage(),
//                request.getGenre(),
//                request.getDurationMinutes(),
//                request.getRating()
//        );
//
//        return repository.save(event);
//    }

    @Cacheable("events")
    @Transactional(readOnly = true)
    public List<Event> getActiveEvents() {
        return repository.findByActiveTrue();
    }

    @Cacheable("events")
    @Transactional(readOnly = true)
    public List<EventResponse> getActiveEventsCached() {
        return repository.findByActiveTrue()
                .stream()
                .map(EventResponse::new)
                .toList();
    }

    @CacheEvict(value = "events", allEntries = true)
    @Transactional
    public void deactivate(UUID eventId) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.deactivate();
    }

    @Cacheable(value = "event", key = "#eventId")
    public Event getEventById(UUID eventId) {
        return repository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }

    @CacheEvict(value = {"events", "event"}, allEntries = true)
    public Event create(CreateEventRequest request) {
        return repository.save(
                new Event(
                        request.getTitle(),
                        request.getDescription(),
                        request.getCategory(),
                        request.getLanguage(),
                        request.getGenre(),
                        request.getDurationMinutes(),
                        request.getRating()
                )
        );
    }


}
