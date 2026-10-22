package com.booking_service.booking_service.dto;

import java.time.LocalDateTime;

import com.booking_service.booking_service.entity.BookingStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {

    private Long bookingId;

    private Long userId;

    private Long eventId;

    private Integer seatsBooked;

    private Double totalAmount;

    private LocalDateTime bookingDate;

    private BookingStatus status;
}