package com.example.mscourierservice.exception;

public class InvalidCourierStatusException extends RuntimeException {
    public InvalidCourierStatusException(String message) {
        super(message);
    }
}
