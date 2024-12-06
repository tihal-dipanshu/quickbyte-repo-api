package com.quickbyte.common.enums;

public enum OrderStatus {
    Pending,
    Processing,
    Preparing,
    Ready,
    Completed,
    Cancelled;

    public static OrderStatus fromString(String value) {
        for (OrderStatus status : values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value for OrderStatus: " + value);
    }
}
