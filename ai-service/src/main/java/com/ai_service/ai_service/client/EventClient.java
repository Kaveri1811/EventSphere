package com.ai_service.ai_service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ai_service.ai_service.dto.EventDTO;

@FeignClient(name = "EVENT-SERVICE")
public interface EventClient {

    @GetMapping("/events")
    List<EventDTO> getEvents();
}