package com.ticketing.eventbooking.booking.service;

import com.ticketing.eventbooking.booking.event.BookingConfirmedEvent;
import com.ticketing.eventbooking.booking.event.BookingEventPublisher;
import com.ticketing.eventbooking.booking.model.Booking;
import com.ticketing.eventbooking.booking.model.ShowSeat;
import com.ticketing.eventbooking.booking.repository.BookingRepository;
import com.ticketing.eventbooking.booking.repository.ShowSeatRepository;
import com.ticketing.eventbooking.show.model.Show;
import com.ticketing.eventbooking.show.repository.ShowRepository;
import com.ticketing.eventbooking.user.model.User;
import com.ticketing.eventbooking.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final BookingEventPublisher eventPublisher;

    public BookingService(
            ShowSeatRepository showSeatRepository,
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            UserRepository userRepository,
            BookingEventPublisher eventPublisher
    ) {
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Booking bookSeats(
            UUID userId,
            UUID showId,
            List<UUID> showSeatIds
    ) {
        // lock seats for this show
        List<ShowSeat> seats =
                showSeatRepository.findAndLockSeats(showId, showSeatIds);

        // validate all requested seats were found and locked
        if (seats.size() != showSeatIds.size()) {
            throw new IllegalStateException(
                    "One or more selected seats are no longer available"
            );
        }

        // Mark seats as booked
        seats.forEach(ShowSeat::lock);
        seats.forEach(ShowSeat::book);

        // Fetch show & user
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new IllegalArgumentException("Show not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create booking
        Booking booking = bookingRepository.save(
                new Booking(user, show, Set.copyOf(seats))
        );

        // publish async confirmation event (non-blocking)
        eventPublisher.publish(
                new BookingConfirmedEvent(
                        booking.getId(),
                        user.getId(),
                        show.getId(),
                        showSeatIds,
                        booking.getBookedAt()
                )
        );

        return booking;
    }
}
