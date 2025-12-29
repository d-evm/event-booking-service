package com.ticketing.eventbooking.venue.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "auditoriums",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_auditorium_name_per_venue",
                        columnNames = {"venue_id", "name"}
                )
        }
)
public class Auditorium extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer totalRows;

    @Column(nullable = false)
    private Integer totalColumns;

    protected Auditorium() {
    }

    public Auditorium(
            Venue venue,
            String name,
            Integer totalRows,
            Integer totalColumns
    ) {
        this.venue = venue;
        this.name = name;
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }
}
