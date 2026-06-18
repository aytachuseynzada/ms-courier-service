package com.example.mscourierservice.exception;

public class NoAvailableCourierFound extends RuntimeException {
    public NoAvailableCourierFound(String message) {
        super(message);
    }
}
