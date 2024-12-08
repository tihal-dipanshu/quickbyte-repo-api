package com.quickbyte.business.command.menu;

import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import com.quickbyte.data.IRepositories.IMenuItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetItemsByCategoryCommand implements MenuCommand<List<MenuItemDTO>> {
    private final IMenuCategoryRepository menuCategoryRepository;
    private final IMenuItemRepository menuItemRepository;
    private final int categoryId;

    public GetItemsByCategoryCommand(IMenuCategoryRepository menuCategoryRepository,
                                     IMenuItemRepository menuItemRepository,
                                     int categoryId) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemRepository = menuItemRepository;
        this.categoryId = categoryId;
    }

    @Override
    public List<MenuItemDTO> execute() {
        if (!menuCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found");
        }

        return menuItemRepository.findByCategoryCategoryIdAndIsAvailableTrue(categoryId).stream()
                .map(this::convertToMenuItemDTO)
                .collect(Collectors.toList());
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
