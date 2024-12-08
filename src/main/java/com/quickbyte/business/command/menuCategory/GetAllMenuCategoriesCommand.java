package com.quickbyte.business.command.menuCategory;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllMenuCategoriesCommand implements MenuCategoryCommand<List<MenuCategoryDTO>> {
    private final IMenuCategoryRepository menuCategoryRepository;

    public GetAllMenuCategoriesCommand(IMenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public List<MenuCategoryDTO> execute() {
        return menuCategoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MenuCategoryDTO convertToDTO(MenuCategory category) {
        return new MenuCategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
