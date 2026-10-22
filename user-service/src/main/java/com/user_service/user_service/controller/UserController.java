package com.user_service.user_service.controller;

import com.user_service.user_service.DTO.UserResponse;
import com.user_service.user_service.Entity.User;
import com.user_service.user_service.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;


    @GetMapping("/{id}")
    public UserResponse getUserById(
            @PathVariable Long id
    ) {

        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );


        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}