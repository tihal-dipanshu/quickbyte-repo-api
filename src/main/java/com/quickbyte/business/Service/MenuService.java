package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.business.IService.IMenuService;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService implements IMenuService {

    private final IMenuCategoryRepository menuCategoryRepository;
    private final IMenuItemRepository menuItemRepository;

    @Autowired
    public MenuService(IMenuCategoryRepository menuCategoryRepository, IMenuItemRepository menuItemRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuCategoryDTO> getAllActiveCategories() {
        return menuCategoryRepository.findByIsActiveTrue().stream()
                .map(this::convertToMenuCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemDTO> getItemsByCategory(int categoryId) {
        if (!menuCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category not found");
        }

        return menuItemRepository.findByCategoryCategoryIdAndIsAvailableTrue(categoryId).stream()
                .map(this::convertToMenuItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemDTO> getPopularItems() {
        // Assuming "popular items" are determined by a custom query
        return menuItemRepository.findPopularItems().stream()
                .map(this::convertToMenuItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemDTO getItemById(int itemId) {
        MenuItem menuItem = menuItemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found"));
        return convertToMenuItemDTO(menuItem);
    }

    @Override
    public List<MenuItemDTO> searchMenuItems(String query) {
        if (query == null || query.trim().isEmpty()) {
            throw new InvalidInputException("Search query cannot be empty");
        }

        return menuItemRepository.findByNameContainingIgnoreCase(query).stream()
                .map(this::convertToMenuItemDTO)
                .collect(Collectors.toList());
    }

    // New method to get all items
    @Override
    public List<MenuItemDTO> getAllItems() {
        return menuItemRepository.getAllItems().stream()
                .map(this::convertToMenuItemDTO)
                .collect(Collectors.toList());
    }

    // Helper methods to convert entities to DTOs
    private MenuCategoryDTO convertToMenuCategoryDTO(MenuCategory category) {
        return new MenuCategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }

    private MenuItemDTO convertToMenuItemDTO(MenuItem item) {
        return new MenuItemDTO(
                item.getItemId(),
                item.getCategory().getCategoryId(), // Change this line
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getImageUrl(),
                item.isAvailable() // Change this line if necessary
        );
    }
}