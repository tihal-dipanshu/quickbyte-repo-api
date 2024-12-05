package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.CreateMenuCategoryDTO;
import com.quickbyte.business.DTO.MenuCategoryDTO;
import com.quickbyte.business.IService.IMenuCategoryService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-categories")
public class MenuCategoryController {

    private final IMenuCategoryService menuCategoryService;

    @Autowired
    public MenuCategoryController(IMenuCategoryService menuCategoryService) {
        this.menuCategoryService = menuCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<MenuCategoryDTO>> getAllMenuCategories() {
        List<MenuCategoryDTO> categories = menuCategoryService.getAllMenuCategories();
        return ResponseEntity.ok(categories);
    }



    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getMenuCategoryById(@PathVariable Integer categoryId) {
        try {
            MenuCategoryDTO category = menuCategoryService.getMenuCategoryById(categoryId);
            return ResponseEntity.ok(category);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Menu category not found: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createMenuCategory(@RequestBody CreateMenuCategoryDTO newCategory) {
        try {
            MenuCategoryDTO createdCategory = menuCategoryService.createMenuCategory(newCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<?> updateMenuCategory(@PathVariable Integer categoryId, @RequestBody CreateMenuCategoryDTO updateCategory) {
        try {
            MenuCategoryDTO updatedCategory = menuCategoryService.updateMenuCategory(categoryId, updateCategory);
            return ResponseEntity.ok(updatedCategory);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Menu category not found: " + e.getMessage()));
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteMenuCategory(@PathVariable Integer categoryId) {
        try {
            menuCategoryService.deleteMenuCategory(categoryId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("Menu category not found: " + e.getMessage()));
        }
    }





}