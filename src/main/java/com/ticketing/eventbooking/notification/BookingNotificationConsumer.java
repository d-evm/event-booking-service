package com.ticketing.eventbooking.notification;

import com.ticketing.eventbooking.booking.event.BookingConfirmedEvent;
import com.ticketing.eventbooking.booking.event.BookingTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingNotificationConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(BookingNotificationConsumer.class);

    @KafkaListener(
            topics = BookingTopics.BOOKING_CONFIRMED,
            groupId = "booking-notification-group"
    )
    public void handleBookingConfirmed(
            BookingConfirmedEvent event
    ) {
        log.info(
                "Sending booking confirmation: bookingId={}, userId={}, seats={}",
                event.getBookingId(),
                event.getUserId(),
                event.getSeatIds()
        );
    }
}
