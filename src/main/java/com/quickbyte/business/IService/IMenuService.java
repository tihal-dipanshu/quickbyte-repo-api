package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.DTO.MenuItemDTO;

import java.util.List;

public interface IMenuService {

    List<MenuCategoryDTO> getAllActiveCategories();

    List<MenuItemDTO> getItemsByCategory(int categoryId);

    List<MenuItemDTO> getPopularItems();

    List<MenuItemDTO> searchMenuItems(String query);

    MenuItemDTO getItemById(int itemId);

    List<MenuItemDTO> getAllItems();
}