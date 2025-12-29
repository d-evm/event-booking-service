package com.ticketing.eventbooking.booking.event;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class BookingConfirmedEvent {

    private final UUID bookingId;
    private final UUID userId;
    private final UUID showId;
    private final List<UUID> seatIds;
    private final Instant bookedAt;

    public BookingConfirmedEvent(
            UUID bookingId,
            UUID userId,
            UUID showId,
            List<UUID> seatIds,
            Instant bookedAt
    ) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showId = showId;
        this.seatIds = seatIds;
        this.bookedAt = bookedAt;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getUserId() {
        return userId;
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
