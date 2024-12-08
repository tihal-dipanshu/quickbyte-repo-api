package com.quickbyte.business.command.menuCategory;

import com.quickbyte.business.DTO.CreateMenuCategoryDTO;
import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

public class UpdateMenuCategoryCommand implements MenuCategoryCommand<MenuCategoryDTO> {
    private final IMenuCategoryRepository menuCategoryRepository;
    private final Integer categoryId;
    private final CreateMenuCategoryDTO menuDto;

    public UpdateMenuCategoryCommand(IMenuCategoryRepository menuCategoryRepository,
                                     Integer categoryId,
                                     CreateMenuCategoryDTO menuDto) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.categoryId = categoryId;
        this.menuDto = menuDto;
    }

    @Override
    public MenuCategoryDTO execute() {
        MenuCategory existingMenu = menuCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("No menu category found for ID: " + categoryId));

        updateFields(existingMenu, menuDto);

        MenuCategory updatedMenu = menuCategoryRepository.save(existingMenu);
        return convertToDTO(updatedMenu);
    }

    private void updateFields(MenuCategory existing, CreateMenuCategoryDTO dto) {
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
    }

    private MenuCategoryDTO convertToDTO(MenuCategory category) {
        return new MenuCategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
