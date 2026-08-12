package com.lucas.taskservice.controller;

import com.lucas.taskservice.dto.request.TaskRequest;
import com.lucas.taskservice.dto.request.UpdateTaskStatusRequest;
import com.lucas.taskservice.dto.response.TaskResponse;
import com.lucas.taskservice.entity.TaskStatus;
import com.lucas.taskservice.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request) {
        var task = taskService.create(request);
        var uri = URI.create("/api/tasks" + task.id());

        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        var tasks = taskService.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable String id) {
        var task = taskService.findById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findByUserIdAndStatus(
            @RequestParam Long userId, @RequestParam(required = false) TaskStatus status) {

        List<TaskResponse> tasks = (status != null) ?
                taskService.findByUserIdAndStatus(userId, status)
                : taskService.findByUserId(userId);

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable String id, @Valid @RequestBody TaskRequest request) {
        var task = taskService.update(id, request);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> updateStatus(@PathVariable String id,
                                                     @Valid @RequestBody UpdateTaskStatusRequest request) {
        var task = taskService.updateStatus(id, request);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
