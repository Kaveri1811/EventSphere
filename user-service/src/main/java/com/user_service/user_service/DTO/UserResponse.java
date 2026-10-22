package com.user_service.user_service.DTO;

import com.user_service.user_service.Entity.Role;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String fullName;

    private String email;

    private Role role;
}