package com.lucas.taskservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    private Long userId;

    private String title;

    private String description;

    @Builder.Default
    private TaskStatus status = TaskStatus.PENDING;

    @Field("created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Field("completed_at")
    private LocalDateTime completedAt;
}
