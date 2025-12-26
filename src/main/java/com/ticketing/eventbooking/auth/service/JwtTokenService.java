package com.ticketing.eventbooking.auth.service;

import com.ticketing.eventbooking.user.model.User;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(User user) {

        Instant now = Instant.now();

        String roles = user.getRoles()
                .stream()
                .map(r -> r.getName().name())
                .collect(Collectors.joining(","));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("event-booking-service")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600))
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", roles)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
