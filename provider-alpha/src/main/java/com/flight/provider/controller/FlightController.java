package com.flight.provider.controller;

import com.flight.provider.dto.FlightDto;
import com.flight.provider.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights") // Сразу закладываем версионирование
public class FlightController {

    private final FlightService flightService;

    // Spring автоматически внедрит FlightService сюда
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDto> searchFlights(
            @RequestParam String departure,
            @RequestParam String arrival) {

        return flightService.searchFlights(departure, arrival);
    }
}