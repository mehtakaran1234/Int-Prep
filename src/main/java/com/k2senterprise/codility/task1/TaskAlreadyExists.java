package com.k2senterprise.codility.task1;

public class TaskAlreadyExists extends RuntimeException {
    private final String title;

    TaskAlreadyExists(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

