package com.ticketing.eventbooking.venue.controller;

import com.ticketing.eventbooking.venue.dto.CreateAuditoriumRequest;
import com.ticketing.eventbooking.venue.dto.CreateVenueRequest;
import com.ticketing.eventbooking.venue.model.Auditorium;
import com.ticketing.eventbooking.venue.model.Venue;
import com.ticketing.eventbooking.venue.service.VenueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin/venues")
@PreAuthorize("hasRole('ADMIN')")
public class AdminVenueController {

    private final VenueService service;

    public AdminVenueController(VenueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Venue> createVenue(
            @Valid @RequestBody CreateVenueRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createVenue(request));
    }

    @PostMapping("/{venueId}/auditoriums")
    public ResponseEntity<Auditorium> createAuditorium(
            @PathVariable UUID venueId,
            @Valid @RequestBody CreateAuditoriumRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createAuditorium(venueId, request));
    }
}
