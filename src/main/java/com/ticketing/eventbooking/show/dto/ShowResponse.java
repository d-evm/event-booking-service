package com.ticketing.eventbooking.show.dto;

import com.ticketing.eventbooking.show.model.Show;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShowResponse {

    private final UUID id;
    private final String eventTitle;
    private final String venueName;
    private final String auditoriumName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public ShowResponse(Show show) {
        this.id = show.getId();
        this.eventTitle = show.getEvent().getTitle();
        this.venueName = show.getAuditorium().getVenue().getName();
        this.auditoriumName = show.getAuditorium().getName();
        this.startTime = show.getStartTime();
        this.endTime = show.getEndTime();
    }

    public UUID getId() {
        return id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getAuditoriumName() {
        return auditoriumName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
