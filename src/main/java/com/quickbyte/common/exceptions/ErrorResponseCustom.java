package com.quickbyte.common.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponseCustom {
    private String message;

    public ErrorResponseCustom(String message) {
        this.message = message;
    }

}