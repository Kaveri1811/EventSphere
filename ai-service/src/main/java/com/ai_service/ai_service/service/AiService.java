package com.ai_service.ai_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ai_service.ai_service.client.BookingClient;
import com.ai_service.ai_service.client.EventClient;
import com.ai_service.ai_service.dto.BookingDTO;
import com.ai_service.ai_service.dto.EventDTO;

@Service
public class AiService {

    private final EventClient eventClient;
    private final BookingClient bookingClient;

    public AiService(EventClient eventClient, BookingClient bookingClient) {
        this.eventClient = eventClient;
        this.bookingClient = bookingClient;
    }

    public Object getEvents() {
        return eventClient.getEvents();
    }

    public Object getUserBookings(Long userId) {
        return bookingClient.getUserBookings(userId);
    }
    
    public List<EventDTO> getRecommendations(Long userId) {

    // Get user's booking history
    List<BookingDTO> bookings =
            bookingClient.getUserBookings(userId);

    // Get all events
    List<EventDTO> events =
            eventClient.getEvents();

    LocalDateTime now = LocalDateTime.now();

    // Event IDs previously booked by the user
    List<Long> bookedEventIds = bookings.stream()
            .filter(booking -> "CONFIRMED".equals(booking.getStatus()))
            .map(BookingDTO::getEventId)
            .toList();

    // Upcoming + available events that user has NOT already booked
    List<EventDTO> availableEvents = events.stream()
            .filter(event -> event.getEventDate().isAfter(now))
            .filter(event -> event.getAvailableSeats() > 0)
            .filter(event -> !bookedEventIds.contains(event.getId()))
            .toList();

    return availableEvents;
}
}