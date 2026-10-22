package com.apigateway_service.apigateway_service.config;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.servlet.function.RequestPredicates.path;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import com.apigateway_service.apigateway_service.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Bean
    RouterFunction<ServerResponse> gatewayRoutes(
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        return route("user-service")
                .route(path("/auth/**"), http())
                .before(uri("http://user-service:8081"))
                .build()

                .and(
                        route("event-service")
                                .route(path("/events/**"), http())
                                .before(jwtAuthenticationFilter)
                                .before(uri("http://event-service:8082"))
                                .build()
                )

                .and(
                        route("booking-service")
                                .route(path("/bookings/**"), http())
                                .before(jwtAuthenticationFilter)
                                .before(uri("http://booking-service:8083"))
                                .build()
                );
    }
}