package ru.practicum.shareit.exception;

public class NotUniqueEmail extends RuntimeException {
    public NotUniqueEmail(String message) {
        super(message);
    }
}
