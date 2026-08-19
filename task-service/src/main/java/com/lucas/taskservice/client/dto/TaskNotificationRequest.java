package com.lucas.taskservice.client.dto;

import com.lucas.taskservice.entity.TaskStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskNotificationRequest(
        @NotBlank @Email
        String recipientEmail,

        @NotBlank
        String recipientName,

        @NotBlank
        String taskTitle,

        String taskDescription,

        @NotNull
        TaskStatus taskStatus
) {}