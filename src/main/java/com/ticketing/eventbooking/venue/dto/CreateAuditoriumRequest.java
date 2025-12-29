package com.ticketing.eventbooking.venue.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAuditoriumRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private Integer totalRows;

    @NotNull
    @Min(1)
    private Integer totalColumns;

    public String getName() {
        return name;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }
}
