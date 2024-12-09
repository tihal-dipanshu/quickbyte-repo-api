package com.quickbyte.business.command.menuCategory;

import com.quickbyte.business.DTO.CreateMenuCategoryDTO;
import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

public class CreateMenuCategoryCommand implements MenuCategoryCommand<MenuCategoryDTO> {
    private final IMenuCategoryRepository menuCategoryRepository;
    private final CreateMenuCategoryDTO menuDto;

    public CreateMenuCategoryCommand(IMenuCategoryRepository menuCategoryRepository, CreateMenuCategoryDTO menuDto) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuDto = menuDto;
    }

    @Override
    public MenuCategoryDTO execute() {
        MenuCategory menu = convertToEntity(menuDto);
        MenuCategory savedMenu = menuCategoryRepository.save(menu);
        return convertToDTO(savedMenu);
    }

    private MenuCategory convertToEntity(CreateMenuCategoryDTO dto) {
        MenuCategory category = new MenuCategory();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setActive(true);
        return category;
    }

    private MenuCategoryDTO convertToDTO(MenuCategory category) {
        return new MenuCategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
