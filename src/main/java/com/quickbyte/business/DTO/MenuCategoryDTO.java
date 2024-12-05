package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

public class MenuCategoryDTO {
    // Getters and setters
    @Setter
    @Getter
    private int categoryId;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;

    // Default constructor
    public MenuCategoryDTO() {}

    // Constructor with all fields
    public MenuCategoryDTO(int categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    // Override toString method
}