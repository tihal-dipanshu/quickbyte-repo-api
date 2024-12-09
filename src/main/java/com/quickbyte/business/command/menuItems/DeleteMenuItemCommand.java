package com.quickbyte.business.command.menuItems;

import com.quickbyte.common.exceptions.MenuItemNotFoundException;
import com.quickbyte.data.IRepositories.IManageMenuItemRepository;

public class DeleteMenuItemCommand implements MenuItemCommand<Void> {
    private final IManageMenuItemRepository manageMenuItemRepository;
    private final Integer itemId;

    public DeleteMenuItemCommand(IManageMenuItemRepository manageMenuItemRepository, Integer itemId) {
        this.manageMenuItemRepository = manageMenuItemRepository;
        this.itemId = itemId;
    }

    @Override
    public Void execute() {
        if (!manageMenuItemRepository.existsById(itemId)) {
            throw new MenuItemNotFoundException("No menu item found for ID: " + itemId);
        }
        manageMenuItemRepository.deleteById(itemId);
        return null;
    }
}