package com.ticketing.eventbooking.event.controller;

import com.ticketing.eventbooking.event.dto.EventResponse;
import com.ticketing.eventbooking.event.service.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("/events")
    public List<EventResponse> getEvents() {
        return service.getActiveEvents()
                .stream()
                .map(EventResponse::new)
                .collect(Collectors.toList());
    }
}
