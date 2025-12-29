package com.ticketing.eventbooking.booking.service;

import com.ticketing.eventbooking.booking.model.ShowSeat;
import com.ticketing.eventbooking.booking.repository.ShowSeatRepository;
import com.ticketing.eventbooking.show.model.Show;
import com.ticketing.eventbooking.venue.model.Seat;
import com.ticketing.eventbooking.venue.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShowSeatInitializer {

    private final SeatRepository seatRepository;
    private final ShowSeatRepository showSeatRepository;

    public ShowSeatInitializer(
            SeatRepository seatRepository,
            ShowSeatRepository showSeatRepository
    ) {
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Transactional
    public void initializeSeatsForShow(Show show) {

        List<Seat> seats = seatRepository
                .findByAuditoriumId(show.getAuditorium().getId());

        List<ShowSeat> showSeats = seats.stream()
                .map(seat -> new ShowSeat(show, seat))
                .toList();

        showSeatRepository.saveAll(showSeats);
    }
}
