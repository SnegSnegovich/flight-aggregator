package com.flight.search.service;

import com.flight.search.client.AlphaClient;
import com.flight.search.client.BetaClient;
import com.flight.search.dto.FlightDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Joiner; // Импортируем новый API Java 25

@Service
public class SearchAggregationService {

    private final AlphaClient alphaClient;
    private final BetaClient betaClient;

    public SearchAggregationService(AlphaClient alphaClient, BetaClient betaClient) {
        this.alphaClient = alphaClient;
        this.betaClient = betaClient;
    }

    public List<FlightDto> searchAll(String departure, String arrival) {
        // 🔥 Новый синтаксис Java 25: используем open() и Joiner
        try (var scope = StructuredTaskScope.open(Joiner.awaitAllSuccessfulOrThrow())) {

            // Запускаем запросы параллельно
            var alphaTask = scope.fork(() -> alphaClient.getFlights(departure, arrival));
            var betaTask = scope.fork(() -> betaClient.getFlights(departure, arrival));

            // В Java 25 join() сам выбросит FailedException, если любой из потоков упадет.
            // Метод throwIfFailed() больше не нужен!
            scope.join();

            // Агрегируем результаты
            List<FlightDto> allFlights = new ArrayList<>();
            allFlights.addAll(alphaTask.get());
            allFlights.addAll(betaTask.get());

            // Сортируем по цене
            allFlights.sort((f1, f2) -> f1.price().compareTo(f2.price()));

            return allFlights;

        } catch (Exception e) {
            throw new RuntimeException("Ошибка при агрегации билетов", e);
        }
    }
}