package com.pharmacy.optican.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class ApiException {

    private final String message;
    private final Exception exception;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ApiException(String message, Exception exception, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.exception = exception;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
