package com.lucas.taskservice.dto.request;

import com.lucas.taskservice.entity.TaskStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskStatusRequest(
        @NotNull(message = "The status is required")
        TaskStatus status
){
}
