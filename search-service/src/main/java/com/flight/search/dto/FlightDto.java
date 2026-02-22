package com.flight.search.dto;

import java.math.BigDecimal;

public record FlightDto(String airline,
                        String flightNumber,
                        String departure,
                        String arrival,
                        BigDecimal price) {
}