package com.ticketing.eventbooking.event.dto;

import com.ticketing.eventbooking.event.model.Event;

import java.util.UUID;

public class EventResponse {

    private UUID id;
    private String title;
    private String category;
    private String language;
    private String genre;
    private int durationMinutes;
    private String rating;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.category = event.getCategory().name();
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

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getRating() {
        return rating;
    }
}
