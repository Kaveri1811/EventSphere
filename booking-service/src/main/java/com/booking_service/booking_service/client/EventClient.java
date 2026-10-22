package com.booking_service.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.booking_service.booking_service.config.FeignConfig;
import com.booking_service.booking_service.dto.EventResponse;

@FeignClient(
        name = "EVENT-SERVICE",
        configuration = FeignConfig.class
)
public interface EventClient {

    @GetMapping("/events/{id}")
    EventResponse getEventById(
            @PathVariable Long id
    );

    @PutMapping("/events/{id}/reduce-seats")
    EventResponse reduceSeats(
            @PathVariable Long id,
            @RequestParam("seats") Integer seats
    );

    @PutMapping("/events/{id}/restore-seats")
    EventResponse restoreSeats(
            @PathVariable Long id,
            @RequestParam("seats") Integer seats
    );
}