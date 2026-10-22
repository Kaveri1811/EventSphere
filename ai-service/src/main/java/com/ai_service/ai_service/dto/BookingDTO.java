package com.ai_service.ai_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookingDTO {

    private Long id;
    private Long userId;
    private Long eventId;
    private Integer seatsBooked;
    private Double totalAmount;
    private LocalDateTime bookingDate;
    private String status;
}