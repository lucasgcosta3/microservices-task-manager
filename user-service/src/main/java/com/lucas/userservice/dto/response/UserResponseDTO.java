package com.lucas.userservice.dto.response;

import java.time.LocalDateTime;

public record UserResponseDTO (
        Long id,
        String name,
        String email,
        String password,
        boolean status,
        LocalDateTime createdAt
){
}
