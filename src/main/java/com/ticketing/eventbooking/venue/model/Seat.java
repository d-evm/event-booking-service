package com.ticketing.eventbooking.venue.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_seat_position",
                        columnNames = {"auditorium_id", "seat_row", "seat_column"}
                )
        }
)
public class Seat extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id", nullable = false)
    private Auditorium auditorium;

    @Column(name = "seat_row", nullable = false)
    private Integer rowNumber;

    @Column(name = "seat_column", nullable = false)
    private Integer columnNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SeatCategory category;

    protected Seat() {
    }

    public Seat(
            Auditorium auditorium,
            Integer rowNumber,
            Integer columnNumber,
            SeatCategory category
    ) {
        this.auditorium = auditorium;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.category = category;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public SeatCategory getCategory() {
        return category;
    }
}
