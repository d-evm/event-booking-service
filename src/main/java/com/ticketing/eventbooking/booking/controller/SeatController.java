package com.ticketing.eventbooking.booking.controller;

import com.ticketing.eventbooking.booking.dto.SeatAvailabilityResponse;
import com.ticketing.eventbooking.booking.repository.ShowSeatRepository;
import com.ticketing.eventbooking.booking.model.ShowSeat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/shows")
public class SeatController {

    private final ShowSeatRepository showSeatRepository;

    public SeatController(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @GetMapping("/{showId}/seats")
    public List<SeatAvailabilityResponse> getSeatAvailability(
            @PathVariable UUID showId
    ) {
        List<ShowSeat> seats = showSeatRepository.findByShowId(showId);

        return seats.stream()
                .map(ss -> new SeatAvailabilityResponse(
                        ss.getId(),
                        ss.getSeat().getRowNumber(),
                        ss.getSeat().getColumnNumber(),
                        ss.getSeat().getCategory(),
                        ss.getStatus()
                ))
                .toList();
    }
}
