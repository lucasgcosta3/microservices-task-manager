package com.lucas.taskservice.dto.request;

import com.lucas.taskservice.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;

public record UpdateTaskStatusRequest(
        @NotBlank(message = "The status is required")
        TaskStatus status
){
}
