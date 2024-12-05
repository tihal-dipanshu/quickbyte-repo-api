package com.quickbyte.common.exceptions;

public class BusinessOwnerNotFoundException extends RuntimeException {
    public BusinessOwnerNotFoundException(String message) {
        super(message);
    }
}