package com.booking_service.booking_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking_service.booking_service.dto.BookingRequest;
import com.booking_service.booking_service.dto.BookingResponse;
import com.booking_service.booking_service.entity.Booking;
import com.booking_service.booking_service.service.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {


    private final BookingService bookingService;



    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody BookingRequest request
    ){

        BookingResponse response =
                bookingService.createBooking(request);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }



    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){

        return ResponseEntity.ok(
                bookingService.getAllBookings()
        );
    }



    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(
            @PathVariable Long id
    ){

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(
            @PathVariable Long userId
    ){

        return ResponseEntity.ok(
                bookingService.getUserBookings(userId)
        );
    }

        @PutMapping("/cancel/{id}")
        public ResponseEntity<BookingResponse> cancelBooking(
                @PathVariable Long id
        ) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(id)
        );
        }

}
