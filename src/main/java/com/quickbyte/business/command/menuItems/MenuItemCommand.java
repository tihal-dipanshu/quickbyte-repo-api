package com.quickbyte.business.command.menuItems;

public interface MenuItemCommand<T> {
    T execute();
}
