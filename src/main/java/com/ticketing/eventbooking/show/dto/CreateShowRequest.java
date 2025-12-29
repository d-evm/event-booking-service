package com.ticketing.eventbooking.show.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateShowRequest {

    @NotNull
    private UUID eventId;

    @NotNull
    private UUID auditoriumId;

    @NotNull
    private LocalDateTime startTime;

    public UUID getEventId() {
        return eventId;
    }

    public UUID getAuditoriumId() {
        return auditoriumId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
}
