package com.user_service.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {


        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Public endpoints
                .requestMatchers(
                        "/auth/register",
                        "/auth/login",
                        "/h2-console/**"
                ).permitAll()


                // Internal service communication
                // Booking-Service calls this through Feign
                .requestMatchers(
                        "/users/**"
                ).permitAll()


                // Other endpoints require authentication
                .anyRequest().authenticated()
            )


            // Allow H2 console iframe
            .headers(headers -> headers
                    .frameOptions(frame -> frame.disable())
            );


        return http.build();
    }
}