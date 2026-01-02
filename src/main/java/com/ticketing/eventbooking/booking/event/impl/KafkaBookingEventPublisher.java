package com.ticketing.eventbooking.booking.event.impl;

import com.ticketing.eventbooking.booking.event.BookingConfirmedEvent;
import com.ticketing.eventbooking.booking.event.BookingEventPublisher;
import com.ticketing.eventbooking.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaBookingEventPublisher implements BookingEventPublisher {

    private static final Logger log =
            LoggerFactory.getLogger(KafkaBookingEventPublisher.class);

    private final KafkaTemplate<String, BookingConfirmedEvent> kafkaTemplate;

    public KafkaBookingEventPublisher(
            KafkaTemplate<String, BookingConfirmedEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(BookingConfirmedEvent event) {
        try {
            kafkaTemplate.send(
                    KafkaConfig.BOOKING_CONFIRMED_TOPIC,
                    event.getBookingId().toString(),
                    event
            );
        } catch (Exception ex) {
            // VERY IMPORTANT: booking must NOT fail due to Kafka
            log.error("Failed to publish BookingConfirmedEvent", ex);
        }
    }
}
