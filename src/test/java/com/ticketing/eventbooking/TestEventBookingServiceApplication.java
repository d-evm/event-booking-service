package com.ticketing.eventbooking;

import org.springframework.boot.SpringApplication;

public class TestEventBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(EventBookingServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
