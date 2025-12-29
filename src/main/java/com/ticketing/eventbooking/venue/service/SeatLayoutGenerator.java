package com.ticketing.eventbooking.venue.service;

import com.ticketing.eventbooking.venue.model.Auditorium;
import com.ticketing.eventbooking.venue.model.Seat;
import com.ticketing.eventbooking.venue.model.SeatCategory;
import com.ticketing.eventbooking.venue.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatLayoutGenerator {

    private final SeatRepository seatRepository;

    public SeatLayoutGenerator(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Transactional
    public void generateSeats(Auditorium auditorium) {

        List<Seat> seats = new ArrayList<>();

        int rows = auditorium.getTotalRows();
        int cols = auditorium.getTotalColumns();

        for (int row = 1; row <= rows; row++) {

            SeatCategory category = determineCategory(row, rows);

            for (int col = 1; col <= cols; col++) {
                seats.add(
                        new Seat(
                                auditorium,
                                row,
                                col,
                                category
                        )
                );
            }
        }

        seatRepository.saveAll(seats);
    }

    private SeatCategory determineCategory(int row, int totalRows) {
        if (row <= totalRows * 0.2) {
            return SeatCategory.PREMIUM;
        }
        if (row <= totalRows * 0.6) {
            return SeatCategory.REGULAR;
        }
        return SeatCategory.ECONOMY;
    }
}
