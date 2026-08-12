package com.lucas.taskservice.repository;

import com.lucas.taskservice.entity.Task;
import com.lucas.taskservice.entity.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);
}
