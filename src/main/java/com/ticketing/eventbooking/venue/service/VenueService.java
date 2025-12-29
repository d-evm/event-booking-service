package com.ticketing.eventbooking.venue.service;

import com.ticketing.eventbooking.venue.dto.CreateAuditoriumRequest;
import com.ticketing.eventbooking.venue.dto.CreateVenueRequest;
import com.ticketing.eventbooking.venue.model.Auditorium;
import com.ticketing.eventbooking.venue.model.Venue;
import com.ticketing.eventbooking.venue.repository.AuditoriumRepository;
import com.ticketing.eventbooking.venue.repository.VenueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final SeatLayoutGenerator seatLayoutGenerator;


    public VenueService(
            VenueRepository venueRepository,
            AuditoriumRepository auditoriumRepository, SeatLayoutGenerator seatLayoutGenerator
    ) {
        this.venueRepository = venueRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.seatLayoutGenerator = seatLayoutGenerator;
    }

    @Transactional
    public Venue createVenue(CreateVenueRequest request) {
        Venue venue = new Venue(
                request.getName(),
                request.getCity(),
                request.getAddress()
        );
        return venueRepository.save(venue);
    }

    @Transactional
    public Auditorium createAuditorium(
            UUID venueId,
            CreateAuditoriumRequest request
    ) {
        Venue venue = venueRepository.findById(venueId)
                .orElseThrow(() -> new IllegalArgumentException("Venue not found"));

        Auditorium auditorium = auditoriumRepository.save(
                new Auditorium(
                        venue,
                        request.getName(),
                        request.getTotalRows(),
                        request.getTotalColumns()
                )
        );

        seatLayoutGenerator.generateSeats(auditorium);

        return auditorium;
    }

}
