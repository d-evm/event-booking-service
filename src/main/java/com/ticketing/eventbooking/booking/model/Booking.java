package com.ticketing.eventbooking.booking.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import com.ticketing.eventbooking.show.model.Show;
import com.ticketing.eventbooking.user.model.User;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "bookings")
public class Booking extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "show_seat_id")
    )
    private Set<ShowSeat> seats;

    @Column(nullable = false)
    private Instant bookedAt;

    protected Booking() {
    }

    public Booking(User user, Show show, Set<ShowSeat> seats) {
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.bookedAt = Instant.now();
    }
}
