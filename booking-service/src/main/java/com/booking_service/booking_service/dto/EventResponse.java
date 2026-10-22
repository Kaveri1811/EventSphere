package com.booking_service.booking_service.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private Long id;

    private String name;

    private String description;

    private String location;

    private LocalDateTime eventDate;

    private Double ticketPrice;

    private Integer totalSeats;

    private Integer availableSeats;
}