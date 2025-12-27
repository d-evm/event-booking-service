package com.ticketing.eventbooking.event.dto;

import com.ticketing.eventbooking.event.model.EventCategory;
import jakarta.validation.constraints.*;

public class CreateEventRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private EventCategory category;

    @NotBlank
    private String language;

    @NotBlank
    private String genre;

    @NotNull
    @Min(1)
    private Integer durationMinutes;

    private String rating;

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
