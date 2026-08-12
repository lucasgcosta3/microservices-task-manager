package com.lucas.taskservice.service;

import com.lucas.taskservice.client.UserClient;
import com.lucas.taskservice.client.dto.UserResponse;
import com.lucas.taskservice.dto.request.TaskRequest;
import com.lucas.taskservice.dto.request.UpdateTaskStatusRequest;
import com.lucas.taskservice.dto.response.TaskResponse;
import com.lucas.taskservice.entity.Task;
import com.lucas.taskservice.entity.TaskStatus;
import com.lucas.taskservice.exception.TaskNotFoundException;
import com.lucas.taskservice.mapper.TaskMapper;
import com.lucas.taskservice.repository.TaskRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserClient userClient;

    public TaskResponse create(TaskRequest request) {

        try {
            userClient.findById(request.userId());
        } catch (FeignException.NotFound e) {
            throw new TaskNotFoundException("User not found with id: " + request.userId());
        } catch (FeignException e) {
            throw new IllegalStateException("Unable to reach user-service to validate userId: " + request.userId(), e);
        }

        var task = taskMapper.toEntity(request);
        var saved = taskRepository.save(task);

        return taskMapper.toTaskResponse(saved);
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
