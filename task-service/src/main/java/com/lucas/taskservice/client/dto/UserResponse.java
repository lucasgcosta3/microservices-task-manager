package com.lucas.taskservice.client.dto;

public record UserResponse (
        Long id,
        String name,
        String email,
        Boolean active
){
}
