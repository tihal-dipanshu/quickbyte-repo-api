package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.CreateMenuItemDTO;
import com.quickbyte.business.DTO.MenuItemDTO;

public interface IMenuItemService {
    MenuItemDTO createMenuItem(CreateMenuItemDTO menuItemDTO);
    MenuItemDTO updateMenuItem(MenuItemDTO menuItemDTO);
//    List<MenuItemDTO> getAllMenuItems();
//    MenuItemDTO getMenuItemById(Integer itemId);itemId
    void deleteMenuItem(Integer itemId);
}
