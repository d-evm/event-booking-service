package com.ticketing.eventbooking.event.dto;

import com.ticketing.eventbooking.event.model.Event;
import com.ticketing.eventbooking.event.model.EventCategory;

import java.util.UUID;

public class EventResponse {

    private final UUID id;
    private final String title;
    private final String description;
    private final EventCategory category;
    private final String language;
    private final String genre;
    private final Integer durationMinutes;
    private final String rating;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.category = event.getCategory();
        this.language = event.getLanguage();
        this.genre = event.getGenre();
        this.durationMinutes = event.getDurationMinutes();
        this.rating = event.getRating();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public EventCategory getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public String getRating() {
        return rating;
    }
}
