package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.CreateMenuCategoryDTO;
import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.IService.IMenuCategoryService;
import com.quickbyte.business.command.menuCategory.*;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuCategoryService implements IMenuCategoryService {

    private final IMenuCategoryRepository menuCategoryRepository;

    @Autowired
    public MenuCategoryService(IMenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    public <T> T executeCommand(MenuCategoryCommand<T> command) {
        return command.execute();
    }

    @Override
    public List<MenuCategoryDTO> getAllMenuCategories() {
        return executeCommand(new GetAllMenuCategoriesCommand(menuCategoryRepository));
    }

    @Override
    public MenuCategoryDTO getMenuCategoryById(Integer categoryId) {
        return executeCommand(new GetMenuCategoryByIdCommand(menuCategoryRepository, categoryId));
    }

    @Override
    public MenuCategoryDTO createMenuCategory(CreateMenuCategoryDTO menuDto) {
        return executeCommand(new CreateMenuCategoryCommand(menuCategoryRepository, menuDto));
    }

    @Override
    public MenuCategoryDTO updateMenuCategory(Integer categoryId, CreateMenuCategoryDTO menuDto) {
        return executeCommand(new UpdateMenuCategoryCommand(menuCategoryRepository, categoryId, menuDto));
    }

    @Override
    public void deleteMenuCategory(Integer categoryId) {
        executeCommand(new DeleteMenuCategoryCommand(menuCategoryRepository, categoryId));
    }
}