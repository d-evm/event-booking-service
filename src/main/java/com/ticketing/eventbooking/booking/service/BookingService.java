package com.ticketing.eventbooking.booking.service;

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
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;

    public BookingService(
            ShowSeatRepository showSeatRepository,
            BookingRepository bookingRepository,
            ShowRepository showRepository,
            UserRepository userRepository
    ) {
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Booking bookSeats(
            UUID userId,
            UUID showId,
            List<UUID> seatIds
    ) {
        List<ShowSeat> seats = showSeatRepository.findAndLockSeats(showId, seatIds);

        seats.forEach(ShowSeat::lock);

        seats.forEach(ShowSeat::book);

        Show show = showRepository.findById(showId)
                .orElseThrow();

        User user = userRepository.findById(userId)
                .orElseThrow();

        Booking booking = new Booking(user, show, Set.copyOf(seats));
        return bookingRepository.save(booking);
    }
}
