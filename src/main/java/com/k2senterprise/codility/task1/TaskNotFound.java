package com.k2senterprise.codility.task1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFound extends RuntimeException {
    public TaskNotFound(Long id) {
        super("Task with id: " + id + " not found.");
    }
}

