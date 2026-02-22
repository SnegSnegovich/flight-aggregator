package com.flight.provider.service;

import com.flight.provider.dto.FlightDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FlightService {

    public List<FlightDto> searchFlights(String departure, String arrival) {
        // В будущем здесь будет поход в БД или сложная логика
        return List.of(
                new FlightDto("Alpha Airlines", "AL-100", departure, arrival, new BigDecimal("150.00")),
                new FlightDto("Alpha Airlines", "AL-101", departure, arrival, new BigDecimal("195.50"))
        );
    }
}