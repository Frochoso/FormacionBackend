package com.example.APIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/passenger/addCliente")
                        .uri("http://backend:8080"))
                .route(r -> r.path("/trip/addViaje")
                        .uri("http://backend:8080"))
                .route(r -> r.path("/ticket/generateTicket/{idCliente}/{idViaje}")
                        .uri("http://backend-frontend:8081"))
                .build();
    }

}
