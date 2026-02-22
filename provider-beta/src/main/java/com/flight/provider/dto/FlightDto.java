package com.flight.provider.dto;

import java.math.BigDecimal;

public record FlightDto(
        String airline,
        String flightNumber,
        String departure,
        String arrival,
        BigDecimal price
) {
}