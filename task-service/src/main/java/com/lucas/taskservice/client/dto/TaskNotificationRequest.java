package com.lucas.taskservice.client.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record TaskNotificationRequest(
        @NotBlank @Email
        String recipientEmail,

        @NotBlank
        String recipientName,

        @NotBlank
        String taskTitle,

        String taskDescription
) {}