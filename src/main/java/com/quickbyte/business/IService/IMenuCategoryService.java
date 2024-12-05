package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.CreateMenuCategoryDTO;
import com.quickbyte.business.DTO.MenuCategoryDTO;
import java.util.List;

public interface IMenuCategoryService {
    List<MenuCategoryDTO> getAllMenuCategories();
    MenuCategoryDTO getMenuCategoryById(Integer categoryId);
    MenuCategoryDTO createMenuCategory(CreateMenuCategoryDTO menuCategoryDTO);
    MenuCategoryDTO updateMenuCategory(Integer categoryId, CreateMenuCategoryDTO menuCategoryDTO);
    void deleteMenuCategory(Integer categoryId);
}