package com.quickbyte.business.command.menu;

import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IMenuItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetPopularItemsCommand implements MenuCommand<List<MenuItemDTO>> {
    private final IMenuItemRepository menuItemRepository;

    public GetPopularItemsCommand(IMenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItemDTO> execute() {
        return menuItemRepository.findPopularItems().stream()
                .map(this::convertToMenuItemDTO)
                .collect(Collectors.toList());
    }

    private MenuItemDTO convertToMenuItemDTO(MenuItem item) {
        return new MenuItemDTO(
                item.getItemId(),
                item.getCategory().getCategoryId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getImageUrl(),
                item.isAvailable()
        );
    }
}
