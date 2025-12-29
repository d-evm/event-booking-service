package com.ticketing.eventbooking.venue.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateVenueRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    private String address;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }
}
