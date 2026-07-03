package com.k2senterprise.codility.task1;

class Response {
    private static final boolean FAILURE = false;
    private final boolean success;
    private final String message;

    private Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    static Response fail(String message) {
        return new Response(FAILURE, message);
    }
}

