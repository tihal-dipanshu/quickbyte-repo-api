package com.quickbyte.business.command.businessOwners;

public interface BusinessOwnerCommand<T> {
    T execute();
}
