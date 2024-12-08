package com.quickbyte.business.command.menu;

import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IMenuItemRepository;

public class GetItemByIdCommand implements MenuCommand<MenuItemDTO> {
    private final IMenuItemRepository menuItemRepository;
    private final int itemId;

    public GetItemByIdCommand(IMenuItemRepository menuItemRepository, int itemId) {
        this.menuItemRepository = menuItemRepository;
        this.itemId = itemId;
    }

    @Override
    public MenuItemDTO execute() {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        return convertToMenuItemDTO(menuItem);
    }

    private MenuItemDTO convertToMenuItemDTO(MenuItem item) {
        return new MenuItemDTO(
                item.getItemId(),
                item.getCategory().getCategoryId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getImageUrl(),
                item.isAvailable()
        );
    }
}
