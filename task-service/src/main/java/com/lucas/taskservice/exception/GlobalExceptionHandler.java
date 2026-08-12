package com.lucas.taskservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ProblemDetail createProblemDetail(
            HttpStatus status,
            String title,
            String detail
    ) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(title);
        problem.setDetail(detail);
        problem.setProperty("timestamp", LocalDateTime.now());

        return problem;
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleTaskNotFoundException(TaskNotFoundException e) {
        return createProblemDetail(
                HttpStatus.NOT_FOUND,
                "Task not found",
                e.getMessage());
    }
}
