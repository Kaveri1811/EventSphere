package com.ai_service.ai_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventDTO {

    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime eventDate;
    private Double ticketPrice;
    private Integer totalSeats;
    private Integer availableSeats;
} 
