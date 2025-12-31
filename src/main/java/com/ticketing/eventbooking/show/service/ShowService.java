package com.ticketing.eventbooking.show.service;

import com.ticketing.eventbooking.booking.service.ShowSeatInitializer;
import com.ticketing.eventbooking.event.model.Event;
import com.ticketing.eventbooking.event.repository.EventRepository;
import com.ticketing.eventbooking.show.dto.CreateShowRequest;
import com.ticketing.eventbooking.show.model.Show;
import com.ticketing.eventbooking.show.repository.ShowRepository;
import com.ticketing.eventbooking.venue.model.Auditorium;
import com.ticketing.eventbooking.venue.repository.AuditoriumRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final EventRepository eventRepository;
    private final AuditoriumRepository auditoriumRepository;
    private final ShowSeatInitializer showSeatInitializer;

    public ShowService(
            ShowRepository showRepository,
            EventRepository eventRepository,
            AuditoriumRepository auditoriumRepository,
            ShowSeatInitializer showSeatInitializer
    ) {
        this.showRepository = showRepository;
        this.eventRepository = eventRepository;
        this.auditoriumRepository = auditoriumRepository;
        this.showSeatInitializer = showSeatInitializer;
    }

    @CacheEvict(value = "shows", allEntries = true)
    @Transactional
    public Show create(CreateShowRequest request) {

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Auditorium auditorium = auditoriumRepository.findById(request.getAuditoriumId())
                .orElseThrow(() -> new IllegalArgumentException("Auditorium not found"));

        LocalDateTime start = request.getStartTime();
        LocalDateTime end = start.plusMinutes(event.getDurationMinutes());

        boolean overlaps = showRepository
                .existsByAuditoriumIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        auditorium.getId(),
                        end,
                        start
                );

        if (overlaps) {
            throw new IllegalStateException("Show overlaps with existing show");
        }

        Show show = showRepository.save(
                new Show(event, auditorium, start, end)
        );

        showSeatInitializer.initializeSeatsForShow(show);

        return show;
    }


    @Transactional(readOnly = true)
    public List<Show> getByEvent(UUID eventId) {
        return showRepository.findByEventId(eventId);
    }

    @Transactional(readOnly = true)
    public List<Show> getByCity(String city) {
        return showRepository.findByAuditoriumVenueCityIgnoreCase(city);
    }

    @Cacheable(value = "shows", key = "#eventId")
    public List<Show> getShowsByEvent(UUID eventId) {
        return showRepository.findByEventId(eventId);
    }

}
