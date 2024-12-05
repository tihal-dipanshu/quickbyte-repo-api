package com.quickbyte.common.exceptions;

public class AppSettingsNotFoundException extends RuntimeException {

    public AppSettingsNotFoundException(String message) {
        super(message);
    }
}