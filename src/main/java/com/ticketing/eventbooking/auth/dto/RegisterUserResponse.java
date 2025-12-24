package com.ticketing.eventbooking.auth.dto;

import java.util.UUID;

public class RegisterUserResponse {

    private final UUID userId;
    private final String email;

    public RegisterUserResponse(UUID userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
