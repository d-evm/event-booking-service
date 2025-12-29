package com.ticketing.eventbooking.booking.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class BookingResponse {

    private final UUID bookingId;
    private final UUID showId;
    private final List<UUID> seatIds;
    private final Instant bookedAt;

    public BookingResponse(
            UUID bookingId,
            UUID showId,
            List<UUID> seatIds,
            Instant bookedAt
    ) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.seatIds = seatIds;
        this.bookedAt = bookedAt;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getShowId() {
        return showId;
    }

    public List<UUID> getSeatIds() {
        return seatIds;
    }

    public Instant getBookedAt() {
        return bookedAt;
    }
}
