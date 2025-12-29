package com.ticketing.eventbooking.show.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import com.ticketing.eventbooking.event.model.Event;
import com.ticketing.eventbooking.venue.model.Auditorium;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "shows",
        indexes = {
                @Index(name = "idx_show_event", columnList = "event_id"),
                @Index(name = "idx_show_start_time", columnList = "start_time")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_auditorium_start_time",
                        columnNames = {"auditorium_id", "start_time"}
                )
        }
)
public class Show extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    protected Show() {
    }

    public Show(
            Event event,
            Auditorium auditorium,
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {
        this.event = event;
        this.auditorium = auditorium;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event getEvent() {
        return event;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
