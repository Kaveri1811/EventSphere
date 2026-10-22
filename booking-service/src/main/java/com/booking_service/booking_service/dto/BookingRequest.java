package com.booking_service.booking_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long eventId;

    @NotNull
    @Min(1)
    private Integer seatsBooked;
}