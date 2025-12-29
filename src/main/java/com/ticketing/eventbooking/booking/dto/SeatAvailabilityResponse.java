package com.ticketing.eventbooking.booking.dto;

import com.ticketing.eventbooking.booking.model.SeatStatus;
import com.ticketing.eventbooking.venue.model.SeatCategory;

import java.util.UUID;

public class SeatAvailabilityResponse {

    private final UUID showSeatId;
    private final int row;
    private final int column;
    private final SeatCategory category;
    private final SeatStatus status;

    public SeatAvailabilityResponse(
            UUID showSeatId,
            int row,
            int column,
            SeatCategory category,
            SeatStatus status
    ) {
        this.showSeatId = showSeatId;
        this.row = row;
        this.column = column;
        this.category = category;
        this.status = status;
    }

    public UUID getShowSeatId() {
        return showSeatId;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public SeatStatus getStatus() {
        return status;
    }
}
