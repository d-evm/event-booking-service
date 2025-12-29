package com.ticketing.eventbooking.booking.controller;

import com.ticketing.eventbooking.booking.dto.BookSeatsRequest;
import com.ticketing.eventbooking.booking.dto.BookingResponse;
import com.ticketing.eventbooking.booking.model.Booking;
import com.ticketing.eventbooking.booking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookSeats(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody BookSeatsRequest request
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());

        Booking booking = bookingService.bookSeats(
                userId,
                request.getShowId(),
                request.getShowSeatIds()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BookingResponse(
                        booking.getId(),
                        booking.getShow().getId(),
                        booking.getSeats()
                                .stream()
                                .map(seat -> seat.getId())
                                .collect(Collectors.toList()),
                        booking.getBookedAt()
                ));
    }
}
