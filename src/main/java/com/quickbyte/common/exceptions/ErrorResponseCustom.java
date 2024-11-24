package com.quickbyte.common.exceptions;

public class ErrorResponseCustom {
    private String message;

    public ErrorResponseCustom(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}