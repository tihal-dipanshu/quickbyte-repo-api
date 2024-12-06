package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.CreateMenuItemDTO;
import com.quickbyte.business.DTO.MenuItemDTO;
import com.quickbyte.business.IService.IMenuItemService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.MenuItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemsController {

    private final IMenuItemService menuItemService;

    @Autowired
    public MenuItemsController(IMenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public ResponseEntity<?> createMenuItem(@RequestBody CreateMenuItemDTO newMenuItem) {
        try {
            MenuItemDTO createdMenuItem = menuItemService.createMenuItem(newMenuItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> updateMenuItem(@RequestBody MenuItemDTO updateMenuItem) {
        try {
            MenuItemDTO updatedMenuItem = menuItemService.updateMenuItem(updateMenuItem);
            return ResponseEntity.ok(updatedMenuItem);
        } catch (MenuItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Menu item not found: " + e.getMessage()));
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

//    @GetMapping
//    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems() {
//        List<MenuItemDTO> menuItems = menuItemService.getAllMenuItems();
//        return ResponseEntity.ok(menuItems);
//    }

//    @GetMapping("/{itemId}")
//    public ResponseEntity<?> getMenuItemById(@PathVariable int itemId) {
//        try {
//            MenuItemDTO menuItem = menuItemService.getMenuItemById(itemId);
//            return ResponseEntity.ok(menuItem);
//        } catch (MenuItemNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponseCustom("Menu item not found: " + e.getMessage()));
//        }
//    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable int itemId) {
        try {
            menuItemService.deleteMenuItem(itemId);
            return ResponseEntity.noContent().build();
        } catch (MenuItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Menu item not found: " + e.getMessage()));
        }
    }
}