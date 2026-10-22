package com.booking_service.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.booking_service.booking_service.config.FeignConfig;
import com.booking_service.booking_service.dto.UserResponse;


@FeignClient(
    name = "USER-SERVICE",
    configuration = FeignConfig.class
)
public interface UserClient {


    @GetMapping("/users/{id}")
    UserResponse getUserById(
            @PathVariable Long id
    );

}