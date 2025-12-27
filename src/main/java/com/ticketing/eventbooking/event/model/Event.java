package com.ticketing.eventbooking.event.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "events",
        indexes = {
                @Index(name = "idx_event_category", columnList = "category"),
                @Index(name = "idx_event_language", columnList = "language")
        }
)
public class Event extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EventCategory category;

    @Column(nullable = false, length = 50)
    private String language;

    @Column(nullable = false, length = 50)
    private String genre;

    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(length = 10)
    private String rating;

    @Column(nullable = false)
    private boolean active = true;

    protected Event() {
    }

    public Event(
            String title,
            String description,
            EventCategory category,
            String language,
            String genre,
            Integer durationMinutes,
            String rating
    ) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.language = language;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
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

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public void updateDetails(
            String title,
            String description,
            String language,
            String genre,
            Integer durationMinutes,
            String rating
    ) {
        this.title = title;
        this.description = description;
        this.language = language;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.rating = rating;
    }
}
