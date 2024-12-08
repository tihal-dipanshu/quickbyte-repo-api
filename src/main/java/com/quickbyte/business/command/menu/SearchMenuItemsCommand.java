package com.quickbyte.business.command.menu;

import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IMenuItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchMenuItemsCommand implements MenuCommand<List<MenuItemDTO>> {
    private final IMenuItemRepository menuItemRepository;
    private final String query;

    public SearchMenuItemsCommand(IMenuItemRepository menuItemRepository, String query) {
        this.menuItemRepository = menuItemRepository;
        this.query = query;
    }

    @Override
    public List<MenuItemDTO> execute() {
        if (query == null || query.trim().isEmpty()) {
            throw new InvalidInputException("Search query cannot be empty");
        }

        return menuItemRepository.findByNameContainingIgnoreCase(query).stream()
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
