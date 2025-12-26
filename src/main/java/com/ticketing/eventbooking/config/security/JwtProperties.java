package com.ticketing.eventbooking.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String secret;
    private long expirationSeconds;

}
