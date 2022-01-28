package ru.geekbrains.lesson_4_spring_boot.service.dto;

import java.time.LocalDateTime;

public class ErrorDto {
    private  String message;

    private LocalDateTime dateTime;

    public ErrorDto() {
        this.dateTime = LocalDateTime.now();
    }

    public ErrorDto(String message) {
        this();
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return dateTime;
    }
}
