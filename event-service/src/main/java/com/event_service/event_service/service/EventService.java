package com.event_service.event_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.event_service.event_service.entity.Event;
import com.event_service.event_service.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;

    public Event create(Event event) {

        // Initially all seats are available
        event.setAvailableSeats(event.getTotalSeats());

        return repository.save(event);
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Event getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event update(Long id, Event updatedEvent) {

        Event event = getById(id);

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setLocation(updatedEvent.getLocation());
        event.setEventDate(updatedEvent.getEventDate());
        event.setTicketPrice(updatedEvent.getTicketPrice());
        event.setTotalSeats(updatedEvent.getTotalSeats());

        // If total seats change, reset available seats
        event.setAvailableSeats(updatedEvent.getTotalSeats());

        return repository.save(event);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Event reduceSeats(Long eventId, Integer seatsBooked) {

        Event event = getById(eventId);

        if (event.getAvailableSeats() < seatsBooked) {
            throw new RuntimeException("Not enough seats available");
        }

        event.setAvailableSeats(event.getAvailableSeats() - seatsBooked);

        return repository.save(event);
    }

    public Event restoreSeats(Long eventId, Integer seatsCancelled) {

        Event event = getById(eventId);

        int updatedSeats = event.getAvailableSeats() + seatsCancelled;

        if (updatedSeats > event.getTotalSeats()) {
            updatedSeats = event.getTotalSeats();
        }

        event.setAvailableSeats(updatedSeats);

        return repository.save(event);
    }
}