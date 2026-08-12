package com.lucas.taskservice.mapper;

import com.lucas.taskservice.dto.request.TaskRequest;
import com.lucas.taskservice.dto.response.TaskResponse;
import com.lucas.taskservice.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task toEntity(TaskRequest request);

    TaskResponse toTaskResponse(Task task);

    List<TaskResponse> toTaskResponse(List<Task> tasks);
}
