package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

public class CreateMenuCategoryDTO {

    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;

    public CreateMenuCategoryDTO() {}

    public CreateMenuCategoryDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

}