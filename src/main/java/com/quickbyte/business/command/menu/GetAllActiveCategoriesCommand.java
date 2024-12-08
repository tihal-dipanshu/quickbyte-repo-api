package com.quickbyte.business.command.menu;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllActiveCategoriesCommand implements MenuCommand<List<MenuCategoryDTO>> {
    private final IMenuCategoryRepository menuCategoryRepository;

    public GetAllActiveCategoriesCommand(IMenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public List<MenuCategoryDTO> execute() {
        return menuCategoryRepository.findByIsActiveTrue().stream()
                .map(this::convertToMenuCategoryDTO)
                .collect(Collectors.toList());
    }

    private MenuCategoryDTO convertToMenuCategoryDTO(MenuCategory category) {
        return new MenuCategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }
}
