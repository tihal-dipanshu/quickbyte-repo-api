package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.business.IService.IMenuService;
import com.quickbyte.business.command.menu.*;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    public <T> T executeCommand(MenuCommand<T> command) {
        return command.execute();
    }

    @Override
    public List<MenuCategoryDTO> getAllActiveCategories() {
        return executeCommand(new GetAllActiveCategoriesCommand(menuCategoryRepository));
    }

    @Override
    public List<MenuItemDTO> getItemsByCategory(int categoryId) {
        return executeCommand(new GetItemsByCategoryCommand(menuCategoryRepository, menuItemRepository, categoryId));
    }

    @Override
    public List<MenuItemDTO> getPopularItems() {
        return executeCommand(new GetPopularItemsCommand(menuItemRepository));
    }

    @Override
    public MenuItemDTO getItemById(int itemId) {
        return executeCommand(new GetItemByIdCommand(menuItemRepository, itemId));
    }

    @Override
    public List<MenuItemDTO> searchMenuItems(String query) {
        return executeCommand(new SearchMenuItemsCommand(menuItemRepository, query));
    }

    @Override
    public List<MenuItemDTO> getAllItems() {
        return executeCommand(new GetAllItemsCommand(menuItemRepository));
    }
}