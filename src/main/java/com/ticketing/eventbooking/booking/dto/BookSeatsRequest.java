package com.ticketing.eventbooking.booking.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class BookSeatsRequest {

    @NotNull
    private UUID showId;

    @NotEmpty
    private List<UUID> showSeatIds;

    public UUID getShowId() {
        return showId;
    }

    public List<UUID> getShowSeatIds() {
        return showSeatIds;
    }
}
