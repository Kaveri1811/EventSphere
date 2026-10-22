package com.event_service.event_service.controller;

import com.event_service.event_service.entity.Event;
import com.event_service.event_service.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @PostMapping
    public Event create(@RequestBody Event event) {
        return service.create(event);
    }

    @GetMapping
    public List<Event> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Event getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Event update(@PathVariable Long id,
                        @RequestBody Event event) {
        return service.update(id, event);
    }

    @PutMapping("/{id}/reduce-seats")
public Event reduceSeats(
        @PathVariable Long id,
        @RequestParam("seats") Integer seats) {

    return service.reduceSeats(id, seats);
}

@PutMapping("/{id}/restore-seats")
public Event restoreSeats(
        @PathVariable Long id,
        @RequestParam("seats") Integer seats) {

    return service.restoreSeats(id, seats);
}

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Event deleted successfully";
    }
}