package com.booking_service.booking_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @NotNull(message = "Number of seats is required")
    @Min(value = 1, message = "At least one seat must be booked")
    private Integer seatsBooked;

    @NotNull(message = "Total amount is required")
    private Double totalAmount;

    @NotNull(message = "Booking date is required")
    private LocalDateTime bookingDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Booking status is required")
    private BookingStatus status;
}