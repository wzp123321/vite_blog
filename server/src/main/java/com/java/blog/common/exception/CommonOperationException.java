package com.java.blog.common.exception;

public class CommonOperationException extends RuntimeException {

    public CommonOperationException() {
    }

    public CommonOperationException(String message) {
        super(message);
    }

    public CommonOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

