package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class MenuItemDTO {
    // Getters and setters
    @Setter
    @Getter
    private int itemId;
    @Setter
    @Getter
    private Integer categoryId;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private BigDecimal price;
    @Setter
    @Getter
    private String imageUrl;
    private boolean isAvailable;

    // Default constructor
    public MenuItemDTO() {}

    // Constructor with all fields
    public MenuItemDTO(int itemId, int categoryId, String name, String description, BigDecimal price, String imageUrl, boolean isAvailable) {
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}