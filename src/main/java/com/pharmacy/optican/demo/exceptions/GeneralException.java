package com.pharmacy.optican.demo.exceptions;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ToString
@Getter
public enum GeneralException {


    USER_NOT_FOUND("User is not present", HttpStatus.NOT_FOUND, "GEEX001", ZonedDateTime.now(ZoneId.of("Z"))),
    USER_ALREADY_EXIST("User already exist", HttpStatus.BAD_REQUEST, "GEEX002", ZonedDateTime.now(ZoneId.of("Z")));

    private final String message;
    private final HttpStatus httpStatus;
    private final String exceptionCode;
    private final ZonedDateTime timestamp;

    GeneralException(String message, HttpStatus httpStatus, String exceptionCode, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.exceptionCode = exceptionCode;
        this.timestamp = timestamp;
    }
}
