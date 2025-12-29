package com.ticketing.eventbooking.booking.event;

public interface BookingEventPublisher {

    void publish(BookingConfirmedEvent event);
}
