package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.CreateMenuCategoryDTO;
import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.IService.IMenuCategoryService;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuCategoryService implements IMenuCategoryService {

    private final IMenuCategoryRepository menuCategoryRepository;

    @Autowired
    public MenuCategoryService(IMenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public List<MenuCategoryDTO> getAllMenuCategories() {
        return menuCategoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuCategoryDTO getMenuCategoryById(Integer categoryId) {
        return menuCategoryRepository.findById(categoryId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No menu category found for ID: " + categoryId));
    }

    @Override
    public MenuCategoryDTO createMenuCategory(CreateMenuCategoryDTO menuDto) {
        MenuCategory menu = convertToEntity(menuDto);
        MenuCategory savedMenu = menuCategoryRepository.save(menu);
        return convertToDTO(savedMenu);
    }

    @Override
    public MenuCategoryDTO updateMenuCategory(Integer categoryId, CreateMenuCategoryDTO menuDto) {
        MenuCategory existingMenu = menuCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("No menu category found for ID: " + categoryId));

        updateFields(existingMenu, menuDto);

        MenuCategory updatedMenu = menuCategoryRepository.save(existingMenu);
        return convertToDTO(updatedMenu);
    }

    @Override
    public void deleteMenuCategory(Integer categoryId) {
        if (!menuCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("No menu category found for ID: " + categoryId);
        }
        menuCategoryRepository.deleteById(categoryId);
    }

    private void updateFields(MenuCategory existing, CreateMenuCategoryDTO dto) {
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
    }

    private MenuCategory convertToEntity(CreateMenuCategoryDTO dto) {
        MenuCategory category = new MenuCategory();
        updateFields(category, dto);
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