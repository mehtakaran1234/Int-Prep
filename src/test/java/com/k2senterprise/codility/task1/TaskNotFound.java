package com.k2senterprise.codility.task1;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFound extends RuntimeException {
    public TaskNotFound(Long id) {
        super("Task with id: " + id + " not found.");
    }
}

