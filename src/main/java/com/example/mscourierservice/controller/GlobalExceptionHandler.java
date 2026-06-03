package com.example.mscourierservice.controller;

import com.example.mscourierservice.exception.CourierNotFoundException;
import com.example.mscourierservice.exception.InvalidCourierStatusException;
import com.example.mscourierservice.exception.NoAvailableCourierFound;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoAvailableCourierFound.class)
    public ErrorResponse handleException(NoAvailableCourierFound ex){
        return new ErrorResponse("not.available.courier.found", ex.getMessage());
    }
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(CourierNotFoundException.class)
    public ErrorResponse handleException(CourierNotFoundException ex){
        return new ErrorResponse("courier.not.found", ex.getMessage());
    }
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(InvalidCourierStatusException.class)
    public ErrorResponse handleException(InvalidCourierStatusException ex){
        return new ErrorResponse("invalid.courier.status", ex.getMessage());
    }
}
