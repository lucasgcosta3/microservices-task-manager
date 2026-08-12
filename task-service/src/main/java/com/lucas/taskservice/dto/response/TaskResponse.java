package com.lucas.taskservice.dto.response;

import com.lucas.taskservice.entity.TaskStatus;

import java.time.LocalDateTime;

public record TaskResponse (
        String id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime completedAt
){

}
