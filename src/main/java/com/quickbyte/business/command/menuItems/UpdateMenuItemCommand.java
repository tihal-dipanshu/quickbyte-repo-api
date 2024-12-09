package com.quickbyte.business.command.menuItems;

import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.MenuItemNotFoundException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IManageMenuItemRepository;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

public class UpdateMenuItemCommand implements MenuItemCommand<MenuItemDTO> {
    private final IManageMenuItemRepository manageMenuItemRepository;
    private final IMenuCategoryRepository menuCategoryRepository;
    private final MenuItemDTO menuItemDTO;

    public UpdateMenuItemCommand(IManageMenuItemRepository manageMenuItemRepository,
                                 IMenuCategoryRepository menuCategoryRepository,
                                 MenuItemDTO menuItemDTO) {
        this.manageMenuItemRepository = manageMenuItemRepository;
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemDTO = menuItemDTO;
    }

    @Override
    public MenuItemDTO execute() {
        validateInput(menuItemDTO);

        MenuItem existingMenuItem = manageMenuItemRepository.findById(menuItemDTO.getItemId())
                .orElseThrow(() -> new MenuItemNotFoundException("No menu item found for ID: " + menuItemDTO.getItemId()));

        updateMenuItemFields(existingMenuItem, menuItemDTO);

        if (menuItemDTO.getCategoryId() != null) {
            MenuCategory category = menuCategoryRepository.findById(menuItemDTO.getCategoryId())
                    .orElseThrow(() -> new InvalidInputException("Invalid category ID"));
            existingMenuItem.setCategory(category);
        }

        MenuItem updatedMenuItem = manageMenuItemRepository.save(existingMenuItem);
        return convertToDTO(updatedMenuItem);
    }

    private void validateInput(MenuItemDTO menuItemDTO) {
        if (menuItemDTO.getName() == null || menuItemDTO.getName().trim().isEmpty()) {
            throw new InvalidInputException("Menu item name cannot be empty");
        }
        if (menuItemDTO.getPrice() == null || menuItemDTO.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Menu item price must be greater than zero");
        }
    }

    private void updateMenuItemFields(MenuItem menuItem, MenuItemDTO dto) {
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        if(dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            menuItem.setImageUrl(dto.getImageUrl());
        } else {
            menuItem.setImageUrl(menuItem.getImageUrl());
        }
        menuItem.setAvailable(dto.isAvailable());
    }

    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setItemId(menuItem.getItemId());
        dto.setCategoryId(menuItem.getCategory() != null ? menuItem.getCategory().getCategoryId() : null);
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        dto.setImageUrl(menuItem.getImageUrl());
        dto.setAvailable(menuItem.isAvailable());
        return dto;
    }
}
