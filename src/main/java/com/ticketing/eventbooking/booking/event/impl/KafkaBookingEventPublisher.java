package com.ticketing.eventbooking.booking.event.impl;

import com.ticketing.eventbooking.booking.event.BookingConfirmedEvent;
import com.ticketing.eventbooking.booking.event.BookingEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaBookingEventPublisher implements BookingEventPublisher {

    private static final Logger log =
            LoggerFactory.getLogger(KafkaBookingEventPublisher.class);

    @Override
    public void publish(BookingConfirmedEvent event) {
        log.info(
                "BookingConfirmedEvent published: bookingId={}, userId={}, seats={}",
                event.getBookingId(),
                event.getUserId(),
                event.getSeatIds()
        );
    }
}
