package com.quickbyte.business.command.menuCategory;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

public class GetMenuCategoryByIdCommand implements MenuCategoryCommand<MenuCategoryDTO> {
    private final IMenuCategoryRepository menuCategoryRepository;
    private final Integer categoryId;

    public GetMenuCategoryByIdCommand(IMenuCategoryRepository menuCategoryRepository, Integer categoryId) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.categoryId = categoryId;
    }

    @Override
    public MenuCategoryDTO execute() {
        return menuCategoryRepository.findById(categoryId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No menu category found for ID: " + categoryId));
    }

    private MenuCategoryDTO convertToDTO(MenuCategory category) {
        return new MenuCategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
