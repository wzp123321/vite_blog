package com.java.blog.common.exception;

public class AdminInsertException extends RuntimeException {

    public AdminInsertException() {
    }

    public AdminInsertException(String message) {
        super(message);
    }

    public AdminInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}

