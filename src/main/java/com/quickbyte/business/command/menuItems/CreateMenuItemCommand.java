package com.quickbyte.business.command.menuItems;

import com.quickbyte.business.DTO.CreateMenuItemDTO;
import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IManageMenuItemRepository;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

import java.math.BigDecimal;

public class CreateMenuItemCommand implements MenuItemCommand<MenuItemDTO> {
    private final IMenuCategoryRepository menuCategoryRepository;
    private final IManageMenuItemRepository manageMenuItemRepository;
    private final CreateMenuItemDTO menuItemDTO;

    public CreateMenuItemCommand(IMenuCategoryRepository menuCategoryRepository,
                                 IManageMenuItemRepository manageMenuItemRepository,
                                 CreateMenuItemDTO menuItemDTO) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.manageMenuItemRepository = manageMenuItemRepository;
        this.menuItemDTO = menuItemDTO;
    }

    @Override
    public MenuItemDTO execute() {
        validateCreateInput(menuItemDTO);

        MenuCategory category = menuCategoryRepository.findById(menuItemDTO.getCategoryId())
                .orElseThrow(() -> new InvalidInputException("Invalid category ID"));

        MenuItem menuItem = convertCreateToEntity(menuItemDTO);
        menuItem.setCategory(category);

        MenuItem savedMenuItem = manageMenuItemRepository.save(menuItem);
        return convertToDTO(savedMenuItem);
    }

    private void validateCreateInput(CreateMenuItemDTO menuItemDTO) {
        if (menuItemDTO.getName() == null || menuItemDTO.getName().trim().isEmpty()) {
            throw new InvalidInputException("Menu item name cannot be empty");
        }
        if (menuItemDTO.getPrice() == null || menuItemDTO.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Menu item price must be greater than zero");
        }
    }

    private MenuItem convertCreateToEntity(CreateMenuItemDTO dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        menuItem.setImageUrl(dto.getImageUrl());
        menuItem.setAvailable(true);
        return menuItem;
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
