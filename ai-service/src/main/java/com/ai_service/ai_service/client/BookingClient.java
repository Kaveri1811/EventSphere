package com.ai_service.ai_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ai_service.ai_service.dto.BookingDTO;

@FeignClient(name = "BOOKING-SERVICE")
public interface BookingClient {

    @GetMapping("/bookings/user/{userId}")
    List<BookingDTO> getUserBookings(
            @PathVariable("userId") Long userId
    );
}