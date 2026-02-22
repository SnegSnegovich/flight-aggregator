package com.flight.search.client;

import com.flight.search.dto.FlightDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import java.util.List;

public interface BetaClient {
    @GetExchange("/api/v1/flights")
    List<FlightDto> getFlights(@RequestParam("departure") String departure, @RequestParam("arrival") String arrival);
}