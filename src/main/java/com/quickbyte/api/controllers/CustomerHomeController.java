package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.business.IService.IMenuService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer/home")
public class CustomerHomeController {

    private final IMenuService menuService;

    @Autowired
    public CustomerHomeController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategories() {
        try {
            List<MenuCategoryDTO> categories = menuService.getAllActiveCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error fetching categories: " + e.getMessage()));
        }
    }

    @GetMapping("/categories/{categoryId}/items")
    public ResponseEntity<?> getItemsByCategory(@PathVariable int categoryId) {
        try {
            List<MenuItemDTO> items = menuService.getItemsByCategory(categoryId);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error fetching items: " + e.getMessage()));
        }
    }

    @GetMapping("/popular-items")
    public ResponseEntity<?> getPopularItems() {
        try {
            List<MenuItemDTO> popularItems = menuService.getPopularItems();
            return ResponseEntity.ok(popularItems);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error fetching popular items: " + e.getMessage()));
        }
    }

    @GetMapping("/search") // http://localhost:8080/api/customer/home/search?query=pizza
    public ResponseEntity<?> searchMenuItems(@RequestParam String query) {
        try {
            List<MenuItemDTO> searchResults = menuService.searchMenuItems(query);
            return ResponseEntity.ok(searchResults);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error searching menu items: " + e.getMessage()));
        }
    }

    @GetMapping("/items/{itemId}")
    public ResponseEntity<?> getItemById(@PathVariable int itemId) {
        try {
            MenuItemDTO item = menuService.getItemById(itemId);
            if (item != null) {
                return ResponseEntity.ok(item);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseCustom("Item not found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseCustom("Error fetching item: " + e.getMessage()));
        }
    }
}
