package com.ai_service.ai_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai_service.ai_service.dto.EventDTO;
import com.ai_service.ai_service.service.AiService;

@RestController
@RequestMapping("/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/events")
    public Object getEvents() {
        return aiService.getEvents();
    }
    @GetMapping("/recommendations/{userId}")
public List<EventDTO> getRecommendations(
        @PathVariable Long userId) {

    return aiService.getRecommendations(userId);
}
}