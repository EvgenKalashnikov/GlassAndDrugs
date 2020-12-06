package com.pharmacy.optican.demo.exceptions;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(GeneralException ex) {
        super(ex.getMessage());
    }
}
