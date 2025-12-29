package com.ticketing.eventbooking.show.controller;

import com.ticketing.eventbooking.show.dto.ShowResponse;
import com.ticketing.eventbooking.show.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService service;

    public ShowController(ShowService service) {
        this.service = service;
    }

    @GetMapping("/event/{eventId}")
    public List<ShowResponse> getByEvent(@PathVariable UUID eventId) {
        return service.getByEvent(eventId)
                .stream()
                .map(ShowResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/city/{city}")
    public List<ShowResponse> getByCity(@PathVariable String city) {
        return service.getByCity(city)
                .stream()
                .map(ShowResponse::new)
                .collect(Collectors.toList());
    }
}
