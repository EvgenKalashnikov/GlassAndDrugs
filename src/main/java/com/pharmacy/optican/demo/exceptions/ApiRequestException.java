package com.pharmacy.optican.demo.exceptions;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(ExceptionCode code) {
        super(code.getMessage());
    }
}
