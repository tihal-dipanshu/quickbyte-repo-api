package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.CreateMenuItemDTO;
import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.business.IService.IMenuItemService;
import com.quickbyte.business.command.menuItems.CreateMenuItemCommand;
import com.quickbyte.business.command.menuItems.DeleteMenuItemCommand;
import com.quickbyte.business.command.menuItems.MenuItemCommand;
import com.quickbyte.business.command.menuItems.UpdateMenuItemCommand;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.MenuItemNotFoundException;
import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IManageMenuItemRepository;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuItemService implements IMenuItemService {

    private final IMenuCategoryRepository menuCategoryRepository;
    private final IManageMenuItemRepository manageMenuItemRepository;

    @Autowired
    public MenuItemService(IMenuCategoryRepository menuCategoryRepository, IManageMenuItemRepository menuItemRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.manageMenuItemRepository = menuItemRepository;
    }

    public <T> T executeCommand(MenuItemCommand<T> command) {
        return command.execute();
    }

    @Override
    public MenuItemDTO createMenuItem(CreateMenuItemDTO menuItemDTO) {
        return executeCommand(new CreateMenuItemCommand(menuCategoryRepository, manageMenuItemRepository, menuItemDTO));
    }

    @Override
    public MenuItemDTO updateMenuItem(MenuItemDTO menuItemDTO) {
        return executeCommand(new UpdateMenuItemCommand(manageMenuItemRepository, menuCategoryRepository, menuItemDTO));
    }

//    @Override
//    public List<MenuItemDTO> getAllMenuItems() {
//        return menuItemRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public MenuItemDTO getMenuItemById(Integer itemId) {
//        return menuItemRepository.findById(itemId)
//                .map(this::convertToDTO)
//                .orElseThrow(() -> new MenuItemNotFoundException("No menu item found for ID: " + itemId));
//    }

    @Override
    public void deleteMenuItem(Integer itemId) {
        executeCommand(new DeleteMenuItemCommand(manageMenuItemRepository, itemId));
    }

//    private void validateInput(MenuItemDTO menuItemDTO) {
//        if (menuItemDTO.getName() == null || menuItemDTO.getName().trim().isEmpty()) {
//            throw new InvalidInputException("Menu item name cannot be empty");
//        }
//        if (menuItemDTO.getPrice() == null || menuItemDTO.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
//            throw new InvalidInputException("Menu item price must be greater than zero");
//        }
//    }
//
//    private void validateCreateInput(CreateMenuItemDTO menuItemDTO) {
//        if (menuItemDTO.getName() == null || menuItemDTO.getName().trim().isEmpty()) {
//            throw new InvalidInputException("Menu item name cannot be empty");
//        }
//        if (menuItemDTO.getPrice() == null || menuItemDTO.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
//            throw new InvalidInputException("Menu item price must be greater than zero");
//        }
//    }

//    private MenuItem convertToEntity(MenuItemDTO dto) {
//        MenuItem menuItem = new MenuItem();
//        updateMenuItemFields(menuItem, dto);
//        return menuItem;
//    }

//    private MenuItem convertCreateToEntity(CreateMenuItemDTO dto) {
//        MenuItem menuItem = new MenuItem();
//        createMenuItemFields(menuItem, dto);
//        return menuItem;
//    }

//    private void updateMenuItemFields(MenuItem menuItem, MenuItemDTO dto) {
//        menuItem.setName(dto.getName());
//        menuItem.setDescription(dto.getDescription());
//        menuItem.setPrice(dto.getPrice());
//        menuItem.setImageUrl(dto.getImageUrl());
//        menuItem.setAvailable(dto.isAvailable());
//    }

//    private void createMenuItemFields(MenuItem menuItem, CreateMenuItemDTO dto) {
//        menuItem.setName(dto.getName());
//        menuItem.setDescription(dto.getDescription());
//        menuItem.setPrice(dto.getPrice());
//        menuItem.setImageUrl(dto.getImageUrl());
//        menuItem.setAvailable(true);
//    }

//    private MenuItemDTO convertToDTO(MenuItem menuItem) {
//        MenuItemDTO dto = new MenuItemDTO();
//        dto.setItemId(menuItem.getItemId());
//        dto.setCategoryId(menuItem.getCategory() != null ? menuItem.getCategory().getCategoryId() : null);
//        dto.setName(menuItem.getName());
//        dto.setDescription(menuItem.getDescription());
//        dto.setPrice(menuItem.getPrice());
//        dto.setImageUrl(menuItem.getImageUrl());
//        dto.setAvailable(menuItem.isAvailable());
//        return dto;
//    }
}