package com.ticketing.eventbooking.booking.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import com.ticketing.eventbooking.show.model.Show;
import com.ticketing.eventbooking.venue.model.Seat;
import jakarta.persistence.*;

@Entity
@Table(
        name = "show_seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_show_seat",
                        columnNames = {"show_id", "seat_id"}
                )
        },
        indexes = {
                @Index(name = "idx_show_seat_status", columnList = "show_id,status")
        }
)
public class ShowSeat extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SeatStatus status;

    protected ShowSeat() {
    }

    public ShowSeat(Show show, Seat seat) {
        this.show = show;
        this.seat = seat;
        this.status = SeatStatus.AVAILABLE;
    }

    public Show getShow() {
        return show;
    }

    public Seat getSeat() {
        return seat;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void lock() {
        if (status != SeatStatus.AVAILABLE) {
            throw new IllegalStateException("Seat not available");
        }
        this.status = SeatStatus.LOCKED;
    }

    public void book() {
        if (status != SeatStatus.LOCKED) {
            throw new IllegalStateException("Seat must be locked before booking");
        }
        this.status = SeatStatus.BOOKED;
    }
}
