package com.ticketing.eventbooking.user.model;

import com.ticketing.eventbooking.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true, length = 20)
    private RoleType name;

    protected Role() {
        // for JPA
    }

    public Role(RoleType name) {
        this.name = name;
    }

    public RoleType getName() {
        return name;
    }
}
