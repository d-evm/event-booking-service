package com.ticketing.eventbooking.venue.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "venues",
        indexes = {
                @Index(name = "idx_venue_city", columnList = "city")
        }
)
public class Venue extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 300)
    private String address;

    protected Venue() {
    }

    public Venue(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
    }

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
