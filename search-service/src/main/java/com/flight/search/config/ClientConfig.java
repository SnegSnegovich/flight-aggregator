package com.flight.search.config;

import com.flight.search.client.AlphaClient;
import com.flight.search.client.BetaClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    // 🔥 1. Обычный RestClient.Builder (Primary).
    // Он нужен самой Эврике (Eureka Client), чтобы она могла делать прямые запросы
    // к серверу Eureka (localhost:8761) БЕЗ попыток балансировки.
    @Bean
    @Primary
    public RestClient.Builder defaultRestClientBuilder() {
        return RestClient.builder();
    }

    // 🔥 2. Балансируемый RestClient.Builder.
    // Используется ТОЛЬКО для наших микросервисов (provider-alpha, provider-beta).
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public AlphaClient alphaClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
        // Обращаемся по ИМЕНИ сервиса из Eureka
        RestClient restClient = builder.baseUrl("http://provider-alpha").build();
        var adapter = RestClientAdapter.create(restClient);
        var factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AlphaClient.class);
    }

    @Bean
    public BetaClient betaClient(@Qualifier("loadBalancedRestClientBuilder") RestClient.Builder builder) {
        // Обращаемся по ИМЕНИ сервиса из Eureka
        RestClient restClient = builder.baseUrl("http://provider-beta").build();
        var adapter = RestClientAdapter.create(restClient);
        var factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(BetaClient.class);
    }
}