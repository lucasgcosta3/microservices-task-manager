package com.lucas.notificationservice.controller;

import com.lucas.notificationservice.dto.TaskNotificationRequest;
import com.lucas.notificationservice.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final EmailService emailService;

    @PostMapping("/task-created")
    public ResponseEntity<Void> notifyTaskCreated(@Valid @RequestBody TaskNotificationRequest request) {
        emailService.sendTaskCreatedEmail(request);
        return ResponseEntity.accepted().build(); // 202 Accepted indica que o processo de notificação foi acionado
    }
}