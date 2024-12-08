package com.quickbyte.business.command.menuCategory;

import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;

public class DeleteMenuCategoryCommand implements MenuCategoryCommand<Void> {
    private final IMenuCategoryRepository menuCategoryRepository;
    private final Integer categoryId;

    public DeleteMenuCategoryCommand(IMenuCategoryRepository menuCategoryRepository, Integer categoryId) {
        this.menuCategoryRepository = menuCategoryRepository;
        this.categoryId = categoryId;
    }

    @Override
    public Void execute() {
        if (!menuCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("No menu category found for ID: " + categoryId);
        }
        menuCategoryRepository.deleteById(categoryId);
        return null;
    }
}
