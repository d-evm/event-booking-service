package com.ticketing.eventbooking.auth.controller;

import com.ticketing.eventbooking.auth.dto.LoginRequest;
import com.ticketing.eventbooking.auth.dto.LoginResponse;
import com.ticketing.eventbooking.auth.dto.RegisterUserRequest;
import com.ticketing.eventbooking.auth.dto.RegisterUserResponse;
import com.ticketing.eventbooking.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(
            @Valid @RequestBody RegisterUserRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
