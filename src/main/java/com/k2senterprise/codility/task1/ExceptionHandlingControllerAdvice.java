package com.k2senterprise.codility.task1;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler(TaskNotFound.class)
    public ResponseEntity<Response> handleTaskNotFound(TaskNotFound ex) {
       /* return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.fail(ex.getMessage()));*/

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(TaskAlreadyExists.class)
    public ResponseEntity<Response> handleTaskAlreadyExists(TaskAlreadyExists ex) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Response.fail(ex.getMessage() != null ? ex.getMessage() : "Task already exists."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.fail(ex.getMessage()));
    }




}
