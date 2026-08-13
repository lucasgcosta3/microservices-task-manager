package com.lucas.taskservice.service;

import com.lucas.taskservice.dto.request.TaskRequest;
import com.lucas.taskservice.dto.request.UpdateTaskStatusRequest;
import com.lucas.taskservice.dto.response.TaskResponse;
import com.lucas.taskservice.entity.Task;
import com.lucas.taskservice.entity.TaskStatus;
import com.lucas.taskservice.exception.TaskNotFoundException;
import com.lucas.taskservice.mapper.TaskMapper;
import com.lucas.taskservice.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskResponse create(TaskRequest request) {
        var task = taskMapper.toEntity(request);
        var saved = taskRepository.save(task);

        return taskMapper.toTaskResponse(saved);
    }

    public List<TaskResponse> findAll() {
        var tasks = taskRepository.findAll();
        return taskMapper.toTaskResponse(tasks);
    }

    public TaskResponse findById(String id) {
        var task = findTaskById(id);
        return taskMapper.toTaskResponse(task);
    }

    public List<TaskResponse> findByUserId(Long userId) {
        var tasks = taskRepository.findByUserId(userId);
        return taskMapper.toTaskResponse(tasks);
    }

    public List<TaskResponse> findByUserIdAndStatus(Long userId, TaskStatus status) {
        var tasks = taskRepository.findByUserIdAndStatus(userId, status);
        return taskMapper.toTaskResponse(tasks);
    }

    public TaskResponse update(String id, TaskRequest request) {
        var task = findTaskById(id);

        task.setTitle(request.title());
        task.setDescription(request.description());

        var saved = taskRepository.save(task);
        return taskMapper.toTaskResponse(saved);
    }

    public TaskResponse updateStatus(String id, UpdateTaskStatusRequest request) {
        var task = findTaskById(id);

        task.setStatus(request.status());

        if (request.status() == TaskStatus.COMPLETED) {
            task.setCompletedAt(LocalDateTime.now());
        } else {
            task.setCompletedAt(null);
        }

        var saved = taskRepository.save(task);
        return taskMapper.toTaskResponse(saved);
    }

    public void delete(String id) {
        var task = findTaskById(id);
        taskRepository.delete(task);
    }

    private Task findTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with " + id));
    }
}
