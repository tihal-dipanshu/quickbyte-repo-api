package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

public class MenuCategoryDTO {
    @Setter
    @Getter
    private int categoryId;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;

    public MenuCategoryDTO() {}

    public MenuCategoryDTO(int categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "MenuCategoryDTO{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}