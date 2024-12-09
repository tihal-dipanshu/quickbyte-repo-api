package com.quickbyte.business.command.order;

public interface OrderCommand<T> {
    T execute();
}
