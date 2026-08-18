package com.lucas.taskservice.client;

import com.lucas.taskservice.client.dto.TaskNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "${application.config.notification-service-url}")
public interface NotificationClient {

    @PostMapping("/api/notifications/email")
    void sendTaskCreatedNotification(@RequestBody TaskNotificationRequest request);
}
