package com.lucas.taskservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest (
        @NotNull(message = "The field 'userId' is required")
        Long userId,

        @NotBlank(message = "The field 'title' is required")
        String title,

        String description
){
}
