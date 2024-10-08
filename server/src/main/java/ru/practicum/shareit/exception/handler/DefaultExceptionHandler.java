package ru.practicum.shareit.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultExceptionHandler {
    public static ResponseEntity<ErrorResponse> response(HttpStatus status, String message, String path) {
        ErrorResponse response = new ErrorResponse(status.value(), message, path);
        return new ResponseEntity<>(response, status);
    }
}