package com.pharmacy.optican.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException exception) {

        String message = exception.getMessage();
        HttpStatus httpStatus = GeneralException.valueOf(message).getHttpStatus();

        ApiException apiException = new ApiException(exception.getMessage(), exception,
            httpStatus, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<Object>(apiException, httpStatus);
    }
}
