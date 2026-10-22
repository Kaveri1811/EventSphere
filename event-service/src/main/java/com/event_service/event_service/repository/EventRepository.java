package com.event_service.event_service.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.event_service.event_service.entity.Event;


public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByLocation(String location);

    List<Event> findByNameContainingIgnoreCase(String name);

    List<Event> findByEventDateAfter(LocalDateTime eventDate);
}