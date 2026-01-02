package com.ticketing.eventbooking.booking.event.consumer;

import com.ticketing.eventbooking.booking.event.BookingConfirmedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingConfirmedConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(BookingConfirmedConsumer.class);

    @KafkaListener(
            topics = "booking.confirmed",
            groupId = "notification-service"
    )
    public void handleBookingConfirmed(BookingConfirmedEvent event) {
        log.info(
                "Sending confirmation for bookingId={}, userId={}, seats={}",
                event.getBookingId(),
                event.getUserId(),
                event.getSeatIds()
        );
    }
}
