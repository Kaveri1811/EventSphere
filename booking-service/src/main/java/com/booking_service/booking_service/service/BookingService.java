package com.booking_service.booking_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking_service.booking_service.client.EventClient;
import com.booking_service.booking_service.client.UserClient;
import com.booking_service.booking_service.dto.BookingRequest;
import com.booking_service.booking_service.dto.BookingResponse;
import com.booking_service.booking_service.dto.EventResponse;
import com.booking_service.booking_service.entity.Booking;
import com.booking_service.booking_service.entity.BookingStatus;
import com.booking_service.booking_service.exception.BookingAlreadyCancelledException;
import com.booking_service.booking_service.exception.BookingNotFoundException;
import com.booking_service.booking_service.exception.EventNotFoundException;
import com.booking_service.booking_service.exception.InsufficientSeatsException;
import com.booking_service.booking_service.exception.UserNotFoundException;
import com.booking_service.booking_service.repository.BookingRepository;

import feign.FeignException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookingService {


    private final BookingRepository bookingRepository;

    private final UserClient userClient;

    private final EventClient eventClient;



    public BookingResponse createBooking(
            BookingRequest request
    ){
  // 1. Check user exists
                        try {
        userClient.getUserById(request.getUserId());
        } catch (FeignException.NotFound ex) {
        throw new UserNotFoundException("User not found");
        }
  // 2. Get event details
                EventResponse event;

        try {
        event = eventClient.getEventById(request.getEventId());
        } catch (FeignException.NotFound ex) {
        throw new EventNotFoundException("Event not found");
        }

 // 3. Check available seats
        if(event.getAvailableSeats() < request.getSeatsBooked()){

            throw new InsufficientSeatsException("Not enough seats available");
        }


        // 4. Calculate amount
        double totalAmount =
                event.getTicketPrice()
                *
                request.getSeatsBooked();



        // 5. Reduce seats in Event Service
        eventClient.reduceSeats(
                request.getEventId(),
                request.getSeatsBooked()
        );



        // 6. Save booking
        Booking booking = Booking.builder()
                .userId(request.getUserId())
                .eventId(request.getEventId())
                .seatsBooked(request.getSeatsBooked())
                .totalAmount(totalAmount)
                .bookingDate(LocalDateTime.now())
                .status(BookingStatus.BOOKED)
                .build();


        Booking saved =
                bookingRepository.save(booking);



        return BookingResponse.builder()
                .bookingId(saved.getId())
                .userId(saved.getUserId())
                .eventId(saved.getEventId())
                .seatsBooked(saved.getSeatsBooked())
                .totalAmount(saved.getTotalAmount())
                .bookingDate(saved.getBookingDate())
                .status(saved.getStatus())
                .build();
    }

        public BookingResponse cancelBooking(Long bookingId) {

    // 1. Find booking
    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() ->
                    new BookingNotFoundException("Booking not found"));

    // 2. Check if already cancelled
    if (booking.getStatus() == BookingStatus.CANCELLED) {
        throw new BookingAlreadyCancelledException("Booking is already cancelled");
    }

    // 3. Restore seats in Event Service
    eventClient.restoreSeats(
            booking.getEventId(),
            booking.getSeatsBooked()
    );

    // 4. Update booking status
    booking.setStatus(BookingStatus.CANCELLED);

    Booking updated = bookingRepository.save(booking);

    // 5. Return response
    return BookingResponse.builder()
            .bookingId(updated.getId())
            .userId(updated.getUserId())
            .eventId(updated.getEventId())
            .seatsBooked(updated.getSeatsBooked())
            .totalAmount(updated.getTotalAmount())
            .bookingDate(updated.getBookingDate())
            .status(updated.getStatus())
            .build();
}

    public List<Booking> getAllBookings(){

        return bookingRepository.findAll();

    }



    public List<Booking> getUserBookings(Long userId){

        return bookingRepository.findByUserId(userId);

    }



    public Booking getBookingById(Long id){

        return bookingRepository.findById(id)
                .orElseThrow(
                    () -> new BookingNotFoundException("Booking not found")
                );
    }

}