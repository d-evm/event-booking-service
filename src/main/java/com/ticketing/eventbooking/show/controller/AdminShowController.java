package com.ticketing.eventbooking.show.controller;

import com.ticketing.eventbooking.show.dto.CreateShowRequest;
import com.ticketing.eventbooking.show.dto.ShowResponse;
import com.ticketing.eventbooking.show.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shows")
@PreAuthorize("hasRole('ADMIN')")
public class AdminShowController {

    private final ShowService service;

    public AdminShowController(ShowService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShowResponse> create(
            @Valid @RequestBody CreateShowRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ShowResponse(service.create(request)));
    }
}
