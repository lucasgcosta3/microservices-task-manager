package com.lucas.taskservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest (
        @NotBlank(message = "The field 'title' is required")
        String title,

        String description
){
}
