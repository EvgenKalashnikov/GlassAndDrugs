package com.pharmacy.optican.demo.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ToString
@Getter
public enum ExceptionCode {

    USER_NOT_FOUND("User is not present", HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z"))),
    USER_ALREADY_EXIST("User already exist", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    ExceptionCode(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
