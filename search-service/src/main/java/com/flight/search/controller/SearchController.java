package com.flight.search.controller;

import com.flight.search.dto.FlightDto;
import com.flight.search.service.SearchAggregationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchAggregationService searchService;

    public SearchController(SearchAggregationService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<FlightDto> search(@RequestParam String departure, @RequestParam String arrival) {
        return searchService.searchAll(departure, arrival);
    }
}