package com.ticketing.eventbooking.event.controller;

import com.ticketing.eventbooking.event.dto.CreateEventRequest;
import com.ticketing.eventbooking.event.dto.EventResponse;
import com.ticketing.eventbooking.event.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/events")
@PreAuthorize("hasRole('ADMIN')")
public class AdminEventController {

    private final EventService service;

    public AdminEventController(EventService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventResponse> create(
            @Valid @RequestBody CreateEventRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EventResponse(service.create(request)));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deactivate(@PathVariable UUID eventId) {
        service.deactivate(eventId);
        return ResponseEntity.noContent().build();
    }
}
