package vn.datnv.srv.advice;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ErrorMessage {
    private final int statusCode;
    private final Instant timestamp;
    private final String message;
    private final String description;

    public ErrorMessage(int statusCode, Instant timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }
}