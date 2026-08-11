package com.lucas.userservice.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        String password,
        Boolean status,
        LocalDateTime createdAt
){
}
